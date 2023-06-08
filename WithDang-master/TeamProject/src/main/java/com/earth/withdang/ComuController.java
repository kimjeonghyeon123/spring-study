package com.earth.withdang;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.earth.domain.ComuDTO;
import com.earth.domain.MemberDto;
import com.earth.domain.PageResolver;
import com.earth.domain.SearchItem;
import com.earth.service.ComuService;

@Controller
@RequestMapping("/dangcomu")
public class ComuController {
	
	@Autowired
	ComuService comuService;
	
	@GetMapping("/list")
	public String list(Integer post_ctgr_id, SearchItem sc, Model m, HttpServletRequest request) {
	    try {
	        List<ComuDTO> list;
	        int totalCnt;
	        
	        if (post_ctgr_id == null) {
	            totalCnt = comuService.getSearchResultCnt(sc);
	            m.addAttribute("totalCnt", totalCnt);
	            list = comuService.getSearchSelectPage(sc);
	        } else {
	            totalCnt = comuService.getCategoryResultCnt(post_ctgr_id, sc);
	            m.addAttribute("totalCnt", totalCnt);
	            list = comuService.getSearchCategoryPage(post_ctgr_id, sc);
	            System.out.println("option = " + sc.getOption());
	        }
	        
	        PageResolver pageResolver = new PageResolver(totalCnt, sc);
	        
	        m.addAttribute("list", list);
	        m.addAttribute("pr", pageResolver);
	        
	        // 추가적인 모델 속성 및 처리
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 예외 처리
	    }
	    
	    return "dangcomu";
	}
	
	@GetMapping("/read")
	public String read(Integer post_id, SearchItem sc, Model m) {
		
		try {
			ComuDTO comuDTO = comuService.readPost(post_id);
			m.addAttribute("comuDTO", comuDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/dangcomu/list";
		}
		
		return "view";
	}
	
	@GetMapping("/post")
	public String post(Model m, HttpServletRequest request) {
		
		if (!loginCheck(request)) {
			return "redirect:/login";
		}
		
		return "write";
		
	}
	
	@PostMapping("/post")
	public String post(ComuDTO comuDTO, RedirectAttributes ra, Model m, HttpSession session) {
		
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		String user_email = memberDto.getUser_email();
		comuDTO.setUser_email(user_email);
		
		try {
			if (comuService.post(comuDTO) != 1)
				throw new Exception("Post Fail");
			ra.addFlashAttribute("msg", "WRT_OK");
			return "redirect:/dangcomu/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("mode", "new");
			m.addAttribute("comuDTO", comuDTO);
			ra.addFlashAttribute("msg", "WRT_ERR");
			return "write";
		}
			
	}
	
	@PostMapping("/delete")
	public String delete(Integer post_id, Integer page, Integer pageSize, 
			RedirectAttributes ra, HttpSession session) {
		
		String user_email = (String) session.getAttribute("member");
		ra.addFlashAttribute("msg", "DEL_OK");
		
		try {
			if(comuService.deletePost(post_id, user_email) != 1) 
				throw new Exception("Delete Fail");
				
		} catch (Exception e) {
			e.printStackTrace();
			ra.addFlashAttribute("msg", "DEL_ERR");
		}
		
		ra.addAttribute("page", page);
		ra.addAttribute("pageSize", pageSize);
		
		return "redirect:/dangcomu/list";
		
	}
	
	@GetMapping("/update")
	public String update(Integer post_id, Model m, HttpSession session, HttpServletRequest request) {
		
		ComuDTO comuDTO;
		
		try {
			comuDTO = comuService.readPost(post_id);
			m.addAttribute("comuDTO", comuDTO); 
			System.out.println(comuDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "edit";
		
	}
	
	@PostMapping("/update")
	public String update(ComuDTO comuDTO, Integer page, Integer pageSize, 
			RedirectAttributes ra, Model m, HttpSession session) {
		
		System.out.println("post_id = " + comuDTO.getPost_id());
		MemberDto member = (MemberDto) session.getAttribute("member");
		String user_email = member.getUser_email();
		comuDTO.setUser_email(user_email);
		
		try {
			if(comuService.updatePost(comuDTO) != 1) 
				throw new Exception("Update Fail");
			ra.addAttribute("page", page); 
			ra.addAttribute("pageSize", pageSize);
			ra.addFlashAttribute("msg", "UPDATE_OK");
			return "redirect:/dangcomu/list";
		} catch (Exception e) { 
			e.printStackTrace(); 
			m.addAttribute("comuDTO", comuDTO); 
			m.addAttribute("page", page); 
			m.addAttribute("pageSize", pageSize); 
			m.addAttribute("msg", "UPDATE_ERR"); 
			return "edit"; 
		}
		
	}
	
	// 로그인 체크
	public boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session.getAttribute("member") != null;
	}
}