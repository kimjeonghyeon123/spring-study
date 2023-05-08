package com.earth.jeonghyeonkim.controller;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.earth.jeonghyeonkim.domain.DangMemberDTO;
import com.earth.jeonghyeonkim.service.DangMemberService;

@Controller
public class RegisterController {

	@Autowired
	private DangMemberService dangMemberService;
	
	@PostMapping("/register")
	public String save(DangMemberDTO dangMemberDTO) throws Exception {
		
		String msg = validator(dangMemberDTO);
		
		if(msg != "") {
			msg = URLEncoder.encode(msg, "utf-8");
			return "redirect:/login/login?msg=" + msg;
		}
		
		dangMemberService.registerDangMember(dangMemberDTO);
		msg = URLEncoder.encode("회원가입 완료", "utf-8");
		
		return "redirect:/login/login?msg=" + msg;
	}

	private String validator(DangMemberDTO dangMemberDTO) throws Exception {
		if(dangMemberDTO.getPwd().length() < 5 || dangMemberDTO.getPwd().length() > 12) {
			return "비밀번호는 5자리 이상 12자리 이하입니다.";
		}
		if(dangMemberService.readDangMember(dangMemberDTO.getEmail()) != null) {
			return "해당 email이 이미 존재합니다.";
		}
		return "";
	}
	
}
