package com.earth.jeonghyeonkim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.earth.jeonghyeonkim.domain.DangBoardDTO;
import com.earth.jeonghyeonkim.domain.PageResolver;
import com.earth.jeonghyeonkim.domain.SearchItem;
import com.earth.jeonghyeonkim.service.DangBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	DangBoardService dangBoardService;
	
	@GetMapping("/list")
	public String boardList(SearchItem sc, Model m, HttpServletRequest request) throws Exception {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL=" + request.getRequestURL();
		}
		
		int totalCnt = dangBoardService.getSearchResultCnt(sc);
		m.addAttribute("totalCnt", totalCnt);
		
		PageResolver pr = new PageResolver(totalCnt, sc);
		
		List<DangBoardDTO> list = dangBoardService.getSearchSelectPage(sc);
		m.addAttribute("list", list);
		m.addAttribute("pr", pr);
		
		return "boardList";
	}
	
	@GetMapping("/read")
	public String read(Integer bno, SearchItem sc, Model m) {
		try {
			DangBoardDTO dangBoardDTO = dangBoardService.read(bno);
			m.addAttribute("dangBoardDTO", dangBoardDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/board/list";
		}
		
		return "board";
	}

	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("email") != null && session.getAttribute("email") != "";
	}

}
