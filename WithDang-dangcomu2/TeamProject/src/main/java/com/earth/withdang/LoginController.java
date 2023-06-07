package com.earth.withdang;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.earth.domain.DogDto;
import com.earth.domain.MemberInfoDto;
import com.earth.domain.MemberDto;
import com.earth.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {
	
	@Autowired
	private MemberService memberservice;

	
	@GetMapping(value="/login")
	public String loginGET() {
		
		return "login";
	}
	
	/* 로그인 */
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginPOST(String user_email,HttpServletRequest request, 
    		HttpServletResponse response, boolean rememberEmail ,MemberDto member, 
    		DogDto dog, RedirectAttributes rttr, Model m) throws Exception{
        
	System.out.println("login 메서드 진입");
    System.out.println("전달된 데이터 : " + member);
	
	MemberDto lvo = memberservice.memberLogin(member);
	DogDto dvo = memberservice.dogSelect(dog);
	//DogDto dog = new DogDto();
	
		if (rememberEmail) {
			//2-2-1. 쿠키를 생성
			//2-2-2. 응답헤더에 저장 			
			Cookie cookie = new Cookie("email", user_email);
			response.addCookie(cookie);
		}

			//2-3-1. 쿠키를 삭제
			//2-3-2. 응답헤더에 저장 	
			Cookie cookie = new Cookie("email", user_email);
			cookie.setMaxAge(0);
			response.addCookie(cookie);

	
		if(lvo == null) {                                // 일치하지 않는 아이디, 비밀번호 입력 경우
        
	        int result = 0;
	        rttr.addFlashAttribute("result", result);
	        return "redirect:/login";
	        
	    }
	
		if(!memberCheck(member)) {
			rttr.addFlashAttribute("msg", "memberCheck");

			HttpSession session = request.getSession();
			session.setAttribute("member", lvo);             // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
			session.setAttribute("dvo", dvo);
			session.setAttribute("nickname", lvo.getUser_nickname());
			
			return "redirect:/mypage";
			}
	
			HttpSession session = request.getSession();
			session.setAttribute("member", lvo);             // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
			session.setAttribute("dvo", dvo);
			session.setAttribute("nickname", lvo.getUser_nickname());
			
		    return "redirect:/";
		
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    private boolean memberCheck(MemberDto member) throws Exception {
    	MemberDto pnum = memberservice.memberCheck(member);
    	System.out.println("member ="+pnum);
    	if(pnum == null) return false;
    	return true;
    }
    
	
}
