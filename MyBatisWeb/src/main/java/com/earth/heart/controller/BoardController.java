package com.earth.heart.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/read")
	public String read(Integer bno, SearchItem sc, Model m) {
		try {
			BoardDTO boardDTO = boardService.read(bno);
			m.addAttribute("boardDTO", boardDTO);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/board/list";
		}
		return "board";
	}

	@PostMapping("/remove")
	public String remove(Integer bno, Integer page, Integer pageSize, RedirectAttributes rattr, HttpSession session) {
		String writer = (String) session.getAttribute("id");
		String msg = "DEL_OK";
		
		try {
			if(boardService.remove(bno, writer) != 1) {
				throw new Exception("Delete failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "DEL_ERR";
		}
		
		//삭제 후 메시지가 한번만 나와야 함
		//모델과 거의 동일한 기능
		//RedirectAttributes에 저장하면 메시지가 한번만 나옴
		//addFlashAttribute() : 한번 저장하고 없어짐. 세션에 잠깐 저장했다가 지워버림
		rattr.addAttribute("page", page);
		rattr.addAttribute("pageSize", pageSize);
		rattr.addFlashAttribute("msg", msg);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/write")
	public String write(Model m) {
		m.addAttribute("mode", "new"); // board.jsp 읽기와 쓰기에 사용. 글쓰기에 사용할 때는 mode=new
		
		return "board";
	}
	
	@PostMapping("/write")
	public String write(BoardDTO boardDTO, RedirectAttributes rattr, Model m, HttpSession session) {
		
		String id = (String) session.getAttribute("id");
		boardDTO.setWriter(id);
		
		try {
			if(boardService.write(boardDTO) != 1) {
				throw new Exception("Write failed");
			}
			rattr.addFlashAttribute("msg", "WRT_OK");
			
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("mode", "new");
			m.addAttribute("boardDTO", boardDTO);
			m.addAttribute("msg", "WRT_ERR");
			
			return "board";
		}
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		//1. 세션을 얻어 (false는 세션이 없어도 새로 생성하지 않음, 반환값이 null이 됨)
		HttpSession session = request.getSession(false);
		//2. 세션에 id가 있는지 확인, 있으면 true를 반환
		return session != null && session.getAttribute("id") != null && session.getAttribute("id") != "";
	}
	
}
