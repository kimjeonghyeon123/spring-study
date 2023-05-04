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
	public String list(SearchItem sc, Model m, HttpServletRequest request) {
		//로그인 했는지 체크
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL=" + request.getRequestURL();
		}
		
		try {
			int totalCnt = dangBoardService.getSearchResultCnt(sc);
			m.addAttribute("totalCnt", totalCnt);
			
			PageResolver pageResolver = new PageResolver(totalCnt, sc);
			
			List<DangBoardDTO> list = dangBoardService.getSearchSelectPage(sc);
			m.addAttribute("list", list);
			m.addAttribute("pr", pageResolver);
			
			
		} catch (Exception e) {e.printStackTrace();}
		
		return "boardList";
	}

	@GetMapping("/read")
	public String read(Integer bno, SearchItem sc, Model m) {
		DangBoardDTO dangBoardDTO;
		try {
			dangBoardDTO = dangBoardService.read(bno);
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
