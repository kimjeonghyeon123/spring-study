package com.earth.jeonghyeonkim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;
import com.earth.jeonghyeonkim.service.DanggeunService;

@Controller
@RequestMapping("/danggeun")
public class DanggeunController {
	
	@Autowired
	private DanggeunService danggeunService;
	
	@GetMapping("/list")
	public String danggeunList(Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL=" + request.getRequestURL();
		}
		
		try {
			List<DanggeunDTO> list = danggeunService.readAll();
			m.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "danggeun";
	}
	
	@GetMapping("/view")
	public String danggeunView(int id, Model m) {
		try {
			DanggeunDTO danggeunDTO = danggeunService.read(id);
			m.addAttribute("danggeunDTO", danggeunDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/danggeun/list";
		}
		return "danggeunView";
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("email") != null && session.getAttribute("email") != "";
	}
	

}
