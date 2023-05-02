package com.earth.heart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.earth.heart.domain.BoardDTO;
import com.earth.heart.domain.PageResolver;
import com.earth.heart.domain.SearchItem;
import com.earth.heart.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public String list(SearchItem sc, Model m, HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/login/login?toURL=" + request.getRequestURL();
		
		try {
			int totalCnt = boardService.getSearchResultCnt(sc);
			m.addAttribute("totalCnt", totalCnt);
			
			PageResolver pageResolver = new PageResolver(totalCnt, sc);
			
			List<BoardDTO> list = boardService.getSearchSelectPage(sc);
			m.addAttribute("list", list);
			m.addAttribute("pr", pageResolver);
			
		} catch (Exception e) {e.printStackTrace();}
		
		return "boardList";		// 로그인 한 상태, 게시물 화면 목록으로 이동
	}

	private boolean loginCheck(HttpServletRequest request) {
		//1. 세션을 얻어 (false는 세션이 없어도 새로 생성하지 않음, 반환값이 null이 됨)
		HttpSession session = request.getSession(false);
		//2. 세션에 id가 있는지 확인, 있으면 true를 반환
		return session != null && session.getAttribute("id") != null && session.getAttribute("id") != "";
	}
	
}
