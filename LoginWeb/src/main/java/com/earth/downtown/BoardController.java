package com.earth.downtown;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
	
		// 로그인 안했으면 로그인 화면으로 이동
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL=" + request.getRequestURL();
		}
		// 로그인 한 상태라면, 게시판 목록 화면으로 이동
		return "boardList";
	}

	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute("id") != null; //true 반환, null이면 false 반환
	}
}
