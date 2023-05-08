package com.earth.jeonghyeonkim.dao;

import com.earth.jeonghyeonkim.domain.DangMemberDTO;

public interface DangMemberDAO {
	
	DangMemberDTO select(String email) throws Exception;
	int insert(DangMemberDTO dangMemberDTO) throws Exception;
	
}
