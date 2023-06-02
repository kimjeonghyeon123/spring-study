package com.earth.jeonghyeonkim.service;

import com.earth.jeonghyeonkim.domain.DangMemberDTO;

public interface DangMemberService {
	
	DangMemberDTO readDangMember(String email) throws Exception;
	int registerDangMember(DangMemberDTO dangMemberDTO) throws Exception;
	
}
