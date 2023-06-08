package com.earth.withdang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.earth.domain.MemberDto;
import com.earth.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class RegisterController {

	@Autowired
	private MemberService memberservice;
		
		@GetMapping("/agreement")
		public String agreeMentGET() {
			return "agreement";
		}
	
		//회원가입 서비스 실행
		@PostMapping("/join")
		public String joinPOST(MemberDto member) throws Exception {
			memberservice.memberJoin(member);
			memberservice.dogInsert(member);
			
			return "redirect:/login";
			
		}
		
		// 아이디 중복 검사
		@RequestMapping(value = "/emailCheck", method = RequestMethod.POST)
		@ResponseBody
		public String memberEmailCheckPOST(String user_email) throws Exception{
			
			int result = memberservice.emailCheck(user_email);
			
			if(result != 0) {
				
				return "fail";	// 중복 아이디가 존재
				
			} else {
				
				return "success";	// 중복 아이디 x
				
			}	
			
			
		} // memberIdChkPOST() 종료	
		
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
						
						
					} // memberIdChkPOST() 종료	
}
