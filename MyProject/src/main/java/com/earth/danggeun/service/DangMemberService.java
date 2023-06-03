package com.earth.danggeun.service;

import com.earth.danggeun.domain.DangMemberDTO;

public interface DangMemberService {
	
	//이메일로 유저정보 가져오기
	DangMemberDTO readDangMember(String email) throws Exception;
	
}
