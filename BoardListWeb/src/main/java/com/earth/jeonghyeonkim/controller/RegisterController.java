package com.earth.jeonghyeonkim.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.earth.jeonghyeonkim.domain.DangMemberDTO;
import com.earth.jeonghyeonkim.service.DangMemberService;

@Controller
public class RegisterController {
	
	@Autowired
	DangMemberService dangMemberService;
	
	@PostMapping("/register")
	public String save(DangMemberDTO dangMemberDTO) throws UnsupportedEncodingException {
		
		// 유효 값 체크
		String msg = validate(dangMemberDTO);
		if(!msg.equals("")) {
			msg = URLEncoder.encode(msg, "utf-8");
			return "redirect:/login/login?msg=" + msg;
		}
		
		try {
			dangMemberService.registerDangMember(dangMemberDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = URLEncoder.encode("회원가입을 완료했습니다.", "utf-8");
		return "redirect:/login/login?msg=" + msg;
	}

	private String validate(DangMemberDTO dangMemberDTO) {
		if(dangMemberDTO.getPwd().length() < 5 || dangMemberDTO.getPwd().length() > 12) {
			return "비밀번호는 5자리 이상 12자리 이하로 입력해주세요.";
		}
		try {
			if(dangMemberService.getDangMember(dangMemberDTO.getEmail()) != null) {
				return "해당 이메일을 사용하는 유저가 존재합니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
