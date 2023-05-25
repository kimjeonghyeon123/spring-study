package com.earth.jeonghyeonkim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;
import com.earth.jeonghyeonkim.domain.DanggeunTypeDTO;
import com.earth.jeonghyeonkim.domain.ZzimDanggeunDTO;
import com.earth.jeonghyeonkim.service.DanggeunService;
import com.earth.jeonghyeonkim.service.DanggeunTypeService;
import com.earth.jeonghyeonkim.service.ZzimDanggeunService;

@Controller
@RequestMapping("/danggeun")
public class DanggeunController {
	
	private DanggeunService danggeunService;
	private DanggeunTypeService danggeunTypeService;
	private ZzimDanggeunService zzimDanggeunService;
	
	public DanggeunController(DanggeunService danggeunService, DanggeunTypeService danggeunTypeService, ZzimDanggeunService zzimDanggeunService) {
		this.danggeunService = danggeunService;
		this.danggeunTypeService = danggeunTypeService;
		this.zzimDanggeunService = zzimDanggeunService;
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
		
		String login_email = (String) request.getSession().getAttribute("email");
		try {
			list = danggeunService.readDanggeunListByOption(type_id, login_email);
			typeList = danggeunTypeService.getTypeList();
			m.addAttribute("type_id", type_id);
			m.addAttribute("list", list);
			m.addAttribute("typeList", typeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(type_id);
		return "danggeun";
	}
	
	@GetMapping("/view")
	public String danggeunView(Integer id, Integer type_id, Model m, HttpServletRequest request) {
		try {
			DanggeunDTO danggeunDTO = danggeunService.readDanggeun(id);
			m.addAttribute("danggeunDTO", danggeunDTO);
			m.addAttribute("type_id", type_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(type_id);
		return "danggeunView";
	}
	
	
	@PostMapping(value = "/togglezzim", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public String toggleZzim(@ModelAttribute ZzimDanggeunDTO zzimDanggeunDTO, @ModelAttribute boolean status) {
	    try {
	        if (status) {
	            zzimDanggeunService.cancelZzim(zzimDanggeunDTO);
	            return "removed";
	        } else {
	            zzimDanggeunService.pushZzim(zzimDanggeunDTO);
	            return "added";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
	
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("email") != null && session.getAttribute("email") != "";
	}
	

}
