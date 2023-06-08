package com.earth.withdang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.earth.domain.MemberDto;
import com.earth.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class RegisterController {

	@Autowired
	private MemberService memberservice;
		
		//비밀번호 인코딩
		@Autowired
		private BCryptPasswordEncoder pwEncoder;
		
		//약관 동의 페이지
		@GetMapping("/agreement")
		public String agreeMentGET() {
			return "agreement";
		}
	
		//회원가입 서비스 실행
		@PostMapping("/join")
		public String joinPOST(MemberDto member, RedirectAttributes rttr) throws Exception {

			String rawPw = "";            // 인코딩 전 비밀번호
	        String encodePw = "";        // 인코딩 후 비밀번호

	        rawPw = member.getUser_pw();	// 비밀번호 데이터 얻음
	        encodePw = pwEncoder.encode(rawPw);	// 비밀번호 인코딩
	        member.setUser_pw(encodePw);		// 인코딩된 비밀번호 member객체에 다시 저장

			memberservice.memberJoin(member);
			memberservice.dogInsert(member);
			
			rttr.addFlashAttribute("msg", "joinOK");
			
			return "redirect:/login";
			
		}
		
		// 이메일 중복 검사
		@RequestMapping(value = "/emailCheck", method = RequestMethod.POST)
		@ResponseBody
		public String memberEmailCheckPOST(String user_email) throws Exception{
			boolean err = false;
			  String regex = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
			  Pattern p = Pattern.compile(regex);
			  Matcher m = p.matcher(user_email);
			  if(m.matches()) {
			int result = memberservice.emailCheck(user_email);
			
			if(result != 0) {
				
				return "fail";	// 중복 아이디가 존재
				
			} else {
				
				return "success";	// 중복 아이디 x
				
			}	
			  } return "fail1";		//이메일 형식 안맞음

		} // memberEmailChkPOST() 종료
		
		// 닉네임 중복 검사
					@RequestMapping(value = "/nickNameCheck", method = RequestMethod.POST)
					@ResponseBody
					public String membernickNameCheckPOST(String user_nickname) throws Exception{
						
						int result = memberservice.nickNameCheck(user_nickname);
						
						if(result != 0) {
							
							return "fail";	// 중복 닉네임 존재
							
						} else {
							
							return "success";	// 중복 닉네임 x
							
						}	
						
						
		} // membernickNameChkPOST() 종료

}
