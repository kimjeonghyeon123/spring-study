package com.earth.danggeun.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.earth.danggeun.domain.DangMemberDTO;
import com.earth.danggeun.service.DangMemberService;

@Controller
@RequestMapping("/login")
public class LoginController {

	private DangMemberService dangMemberService;

	public LoginController(DangMemberService dangMemberService) {
		this.dangMemberService = dangMemberService;
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(String email, String pwd, String toURL, boolean rememberId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(!loginCheck(email, pwd)) {
			String msg = URLEncoder.encode("아이디, 비밀번호를 잘못 입력했습니다.", "utf-8");
		}

		Cookie cookie = new Cookie("email", email);
		
		if(rememberId) {
			response.addCookie(cookie);
		}
		else {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		
		toURL = toURL == null || toURL.equals("") ? "/" : toURL;
		return "redirect:" + toURL;
	}
	
	private boolean loginCheck(String email, String pwd) throws Exception {
		DangMemberDTO dangMemberDTO = dangMemberService.readDangMember(email);
		if(dangMemberDTO == null) {
			return false;
		}
		
		return dangMemberDTO.getPwd().equals(pwd);
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	
}















