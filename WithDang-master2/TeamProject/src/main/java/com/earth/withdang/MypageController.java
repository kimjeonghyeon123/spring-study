package com.earth.withdang;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.earth.domain.DogDto;
import com.earth.domain.MemberDto;
import com.earth.service.MemberService;
import com.earth.service.MypageService;

import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class MypageController {
	
	@Autowired
	private MypageService myService;
	
	@Autowired
	private MemberService memberService;
	
	//마이페이지
	@GetMapping("/mypage")
	public void mypageGET () {

	}
	
	//마이페이지 수정하기 페이지
	@GetMapping("/mypage_update")
	public void myPage_UpdateGET() {
		
	}
	
	//마이페이지 채팅 페이지
	@GetMapping("/mypage_chat")
	public void myPage_chatGET() {

	}
	
	//마이페이지 마이댕근 페이지
	@GetMapping("/myDangguen")
	public void myDangguenGET() {

	}
	
	//마이페이지 마이케어 페이지
	@GetMapping("/myCare")
	public void myCareGET() {

	}
	
	//마이페이지 비밀번호 재설정 페이지
	@GetMapping("/pwReset")
	public void pwRestGET() {
		
	}

	//회원정보수정 실행
	@RequestMapping(value = "/mypage_update", method = RequestMethod.POST)
	public String memberUpdate(HttpServletRequest request, MemberDto member, DogDto dog, RedirectAttributes rttr) throws Exception {
		
		System.out.println("memberUpdate 메서드 진입");
        System.out.println("전달된 데이터 : " + member);
       
        myService.memberUpdate(member);
        myService.dogUpdate(dog);
        MemberDto mvo = memberService.memberSelect(member);
        DogDto dvo = memberService.dogSelect(dog);
        
        rttr.addFlashAttribute("msg", "updateOK");
        
        HttpSession session = request.getSession();
		session.setAttribute("member", mvo);
		session.setAttribute("dvo", dvo);
		
		return "redirect:/mypage";
	}
}	