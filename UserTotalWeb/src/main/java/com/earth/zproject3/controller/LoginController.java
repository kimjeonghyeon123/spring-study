package com.earth.zproject3.controller;

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

import com.earth.zproject3.dao.DangMemberDao;
import com.earth.zproject3.domain.DangMember;

@Controller
public class LoginController {
	
	@Autowired
	DangMemberDao dangMemberDao;
	final int FAIL = 0;
	
	@GetMapping("/login/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/login/login")
	public String login(String email, String pwd, String toURL, boolean rememberId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if(!loginCheck(email, pwd)) {
			String msg = URLEncoder.encode("email 또는 pwd가 일치하지 않습니다.", "utf-8");
			return "redirect:/login/login?msg=" + msg;
		}
		System.out.println("-------------");
		if(rememberId) {
			Cookie cookie = new Cookie("email", email);
			response.addCookie(cookie);
		}
		else {
			Cookie cookie = new Cookie("email", email);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		
		toURL = toURL == null || toURL.equals("") ? "/" : toURL;
		
		return "redirect:" + toURL;
	}

	private boolean loginCheck(String email, String pwd) {
		DangMember dangMember = dangMemberDao.selectDangMember(email);
		if(dangMember == null) {
			return false;
		}
		return dangMember.getPwd().equals(pwd);
	}
	
	@GetMapping("/login/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
