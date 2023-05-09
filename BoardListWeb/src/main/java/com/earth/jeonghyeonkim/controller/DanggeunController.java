package com.earth.jeonghyeonkim.controller;

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

import com.earth.jeonghyeonkim.domain.DanggeunDTO;
import com.earth.jeonghyeonkim.service.DanggeunService;

@Controller
@RequestMapping("/danggeun")
public class DanggeunController {
	
	@Autowired
	private DanggeunService danggeunService;
	
	@GetMapping("/list")
	public String danggeunList(Integer option, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL=" + request.getRequestURL();
		}
		
		if(option == null) {
			option = 0;
		}
				
		try {
			List<DanggeunDTO> list;
			if(option == 0) {
				list = danggeunService.readAll();
			}
			else {
				list = danggeunService.searchItems(option);
			}
			m.addAttribute("list", list);
			m.addAttribute("option", option);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "danggeun";
	}
	
	@GetMapping("/view")
	public String danggeunView(Integer option, int id, Model m) {
		try {
			DanggeunDTO danggeunDTO = danggeunService.read(id);
			m.addAttribute("danggeunDTO", danggeunDTO);
			m.addAttribute("option", option);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/danggeun/list";
		}
		return "danggeunView";
	}
	
	@GetMapping("/write")
	public String danggeunWriteForm(String option, Model m) {
		m.addAttribute("option", option);
		return "danggeunwrite";
	}
	
	@PostMapping("/write")
	public String danggeunWrite(Integer option, DanggeunDTO danggeunDTO, RedirectAttributes rattr, Model m, HttpSession session) {
		danggeunDTO.setWriter((String) session.getAttribute("email"));
		danggeunDTO.setType_id(option);
		
		try {
			if(danggeunService.register(danggeunDTO) != 1) {
				throw new Exception("Write failed");
			}
			rattr.addFlashAttribute("msg", "WRT_OK");
			return "redirect:/danggeun/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("danggeunDTO", danggeunDTO);
			m.addAttribute("option", option);
			m.addAttribute("msg", "WRT_ERR");
			return "danggeunwrite";
		}
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("email") != null && session.getAttribute("email") != "";
	}
	

}
