package com.earth.jeonghyeonkim.dao;

import com.earth.jeonghyeonkim.domain.DangMemberDTO;

public interface DangMemberDAO {
	
	//email로 멤버 정보 불러올 때 사용
	DangMemberDTO select(String email) throws Exception;
	
	//멤버 새로 만들 때 사용 
	int insert(DangMemberDTO dangMemberDTO) throws Exception;
	
	//email로 멤버 이름 불러올 때 사용
	String selectMemberName(String email) throws Exception;
	
}
