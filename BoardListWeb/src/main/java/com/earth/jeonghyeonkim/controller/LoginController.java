package com.earth.jeonghyeonkim.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.earth.jeonghyeonkim.domain.DangMemberDTO;
import com.earth.jeonghyeonkim.service.DangMemberService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	DangMemberService dangMemberService;
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(String email, String pwd, String toURL, boolean rememberId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		// 아이디, 비밀번호 체크
		if(!loginCheck(email, pwd)) {
			String msg = URLEncoder.encode("아이디, 비밀번호를 잘못 입력했습니다.", "utf-8");
			return "redirect:/login/login?msg=" + msg;
		}
		
		// 아이디 기억 쿠기 설정
		if(rememberId) {
			Cookie cookie = new Cookie("email", email);
			response.addCookie(cookie);
		}
		else {
			Cookie cookie = new Cookie("email", email);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		//세션 설정
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		
		// 로그인 후 복귀 URL 설정
		toURL = toURL.equals("") || toURL == null ? "/" : toURL;
		return "redirect:" + toURL;		
	}

	private boolean loginCheck(String email, String pwd) {
		try {
			DangMemberDTO dangMemberDTO = dangMemberService.getDangMember(email);
			return dangMemberDTO.getPwd().equals(pwd);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
