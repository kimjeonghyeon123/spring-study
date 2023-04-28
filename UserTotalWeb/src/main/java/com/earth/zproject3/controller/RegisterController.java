package com.earth.zproject3.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.earth.zproject3.dao.DangMemberDao;
import com.earth.zproject3.domain.DangMember;

@Controller
public class RegisterController {
	
	@Autowired
	DangMemberDao dangMemberDao;
	
	@PostMapping("/register")
	public String save(DangMember dangMember) throws UnsupportedEncodingException {
		String msg = validator(dangMember);
		if(!msg.equals("")) {
			msg = URLEncoder.encode(msg, "utf-8");
			return "redirect:/login/login?msg=" + msg;
		}
		else {
			dangMemberDao.insertDangMember(dangMember);
			msg = URLEncoder.encode("회원가입이 완료되었습니다.", "utf-8");
			return "redirect:/login/login?msg=" + msg;
		}
	}

	private String validator(DangMember dangMember) {
		String email = dangMember.getEmail();
		if(dangMemberDao.selectDangMember(email) != null) {
			return "중복된 아이디입니다.";
		}
		if(dangMember.getPwd().length() < 5 || dangMember.getPwd().length() > 12) {
			return "비밀번호를 5자리 이상 12자리 이하로 입력하세요.";
		}
		return "";
	}
}
