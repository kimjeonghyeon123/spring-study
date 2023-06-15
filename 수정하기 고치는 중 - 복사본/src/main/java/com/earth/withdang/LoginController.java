package com.earth.withdang;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.earth.domain.MemberDto;
import com.earth.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {
	
	@Autowired
	private MemberService memberservice;

	@Autowired
	private BCryptPasswordEncoder pwEncoder;

	//로그인 페이지
	@GetMapping(value="/login")
	public String loginGET() {
		
		return "login";
	}
	
	//이메일 찾기 페이지
	@GetMapping(value="/emailFind")
	public String emailFindGET() {
		return "emailFind";
	}
	
	//비밀번호 찾기 페이지
	@GetMapping(value="/pwdFind")
	public String pwdFindGET() {
		return "pwdFind";
	}

	//이메일 찾기 실행
	@PostMapping("/emailFindRes")
	public String findEmail(MemberDto member, Model m) throws Exception {
		MemberDto email = memberservice.findEmail(member);

		if(email == null) {
			m.addAttribute("check", 1);
		} else {
			m.addAttribute("check", 0);
			m.addAttribute("email", email.getUser_email());
		}

		return "/emailFindRes";
	}

	//비밀번호 찾기 실행
	@PostMapping("/pwdFindRes")
	public String findPwd(MemberDto member, Model m, HttpServletRequest request) throws Exception {
		MemberDto pwd = memberservice.findPwd(member);

		HttpSession session = request.getSession();


		if(pwd == null) {
			m.addAttribute("check", 1);
		} else {
			session.setAttribute("pwd", pwd);
			m.addAttribute("check", 0);
		}

		return "/pwdFindRes";

	}
	
	//비밀번호 변경 실행
	@PostMapping("/pwUpdate")
	public String pwUpdate(MemberDto member, RedirectAttributes rttr, HttpSession session) throws Exception {
		String rawPw = "";
		String encodePw = "";

		rawPw = member.getUser_pw();
		encodePw = pwEncoder.encode(rawPw);
		member.setUser_pw(encodePw);

		memberservice.pwUpdate(member);
		session.invalidate();
		
		rttr.addFlashAttribute("msg", "pwUdate");

		return "redirect:/login";
	}

	/* 로그인 실행 */
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginPOST(String user_email,HttpServletRequest request, 
    		HttpServletResponse response, boolean rememberEmail ,MemberDto member, 
    		DogDto dog, RedirectAttributes rttr) throws Exception{
        
	System.out.println("login 메서드 진입");
    System.out.println("전달된 데이터 : " + member);
	
    HttpSession session = request.getSession();
    String rawPw = "";
    String encodePw = "";

	MemberDto lvo = memberservice.memberLogin(member);
	DogDto dvo = memberservice.dogSelect(dog);
	
		if (rememberEmail) {
			//2-2-1. 쿠키를 생성
			//2-2-2. 응답헤더에 저장 			
			Cookie cookie = new Cookie("email", user_email);
			response.addCookie(cookie);
		} else {

			//2-3-1. 쿠키를 삭제
			//2-3-2. 응답헤더에 저장 	
			Cookie cookie = new Cookie("email", user_email);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		if(lvo != null) {                                // 일치하지 하는 아이디 존재시

			rawPw = member.getUser_pw();        // 사용자가 제출한 비밀번호
            encodePw = lvo.getUser_pw();        // 데이터베이스에 저장한 인코딩된 비밀번호
        
            if(true == pwEncoder.matches(rawPw, encodePw)) {        // 비밀번호 일치여부 판단
                session.setAttribute("member", lvo);     // session에 사용자의 정보 저장
                session.setAttribute("dvo", dvo);
                session.setAttribute("nickname", lvo.getUser_nickname());
		if(!memberCheck(member)) {
			rttr.addFlashAttribute("msg", "memberCheck");

			session.setAttribute("member", lvo);             // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
			session.setAttribute("dvo", dvo);
			session.setAttribute("nickname", lvo.getUser_nickname());
			return "redirect:/mypage";
			}
	
                return "redirect:/";        // 메인페이지 이동

            } else {
            	rttr.addFlashAttribute("result", 0);
                return "redirect:/login";    // 로그인 페이지로 이동
            }
			
	    } else {										//일치하는 아이디가 존재하지 않을 시(로그인 실패)

	    	rttr.addFlashAttribute("result", 0);
	    	return "redirect:/login";
	    }
		
    }
    
    //로그아웃 실행
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    //신규 회원 확인 실행
    private boolean memberCheck(MemberDto member) throws Exception {
    	MemberDto pnum = memberservice.memberCheck(member);
    	System.out.println("member ="+pnum);
    	if(pnum == null) return false;
    	return true;
    }
    
	
}
