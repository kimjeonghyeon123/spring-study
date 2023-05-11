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
import com.earth.jeonghyeonkim.domain.DanggeunTypeDTO;
import com.earth.jeonghyeonkim.service.DanggeunService;
import com.earth.jeonghyeonkim.service.DanggeunTypeService;

@Controller
@RequestMapping("/danggeun")
public class DanggeunController {
	
	private DanggeunService danggeunService;
	private DanggeunTypeService danggeunTypeService;
	
	public DanggeunController(DanggeunService danggeunService, DanggeunTypeService danggeunTypeService) {
		this.danggeunService = danggeunService;
		this.danggeunTypeService = danggeunTypeService;
	}

	@GetMapping("/list")
	public String danggeunList(Integer type_id, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL=" + request.getRequestURL();
		}
		
		if(type_id == null) {
			type_id = 0;
		}
		
		List<DanggeunDTO> list = null;
		List<DanggeunTypeDTO> typeList = null;
		
		try {
			list = danggeunService.readDanggeunListByOption(type_id);
			typeList = danggeunTypeService.getTypeList();
			m.addAttribute("type_id", type_id);
			m.addAttribute("list", list);
			m.addAttribute("typeList", typeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "danggeun";
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("email") != null && session.getAttribute("email") != "";
	}
	

}
