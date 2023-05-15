package com.earth.jeonghyeonkim.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		return "danggeun";
	}
	
	@GetMapping("/view")
	public String danggeunView(Integer id, Integer type_id, Model m, HttpServletRequest request) {
		try {
			DanggeunDTO danggeunDTO = danggeunService.readDanggeun(id, (String) request.getSession().getAttribute("email"));
			m.addAttribute("danggeunDTO", danggeunDTO);
			m.addAttribute("type_id", type_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "danggeunView";
	}
	
	@GetMapping("/write")
	public String danggeunWrite(Integer id, Model m, HttpServletRequest request) {
		List<DanggeunTypeDTO> typeList = null;
		
		try {
			typeList = danggeunTypeService.getTypeList();
			m.addAttribute("typeList", typeList);
			if(id != null) {
				DanggeunDTO danggeunDTO = danggeunService.loadDanggeun(id);
				m.addAttribute("danggeunDTO", danggeunDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "danggeunwrite";
	}
	
	@PostMapping("/write")
	public String registerDanggeun(DanggeunDTO danggeunDTO) {
		
		try {
			danggeunService.registerDanggeun(danggeunDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/danggeun/list";
	}
	
	@PostMapping(value = "/togglezzim", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public String toggleZzim(@ModelAttribute ZzimDanggeunDTO zzimDanggeunDTO) {
	    try {
	        if (zzimDanggeunService.checkZzim(zzimDanggeunDTO)) {
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
	
	@GetMapping("/remove")
	public String remove(Integer id, String writer_email, Integer type_id, RedirectAttributes rattr, Model m) {
		try {
			if(danggeunService.remove(id, writer_email)!=1) {
				throw new Exception("Delete failed");
			}
			rattr.addAttribute("msg", "DEL_OK");
			return "redirect:/danggeun/list?type_id=" + type_id;
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("type_id", type_id);
			return "danggeun";
		}
		
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("email") != null && session.getAttribute("email") != "";
	}
	

}
