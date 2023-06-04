package com.earth.danggeun.dao;

import com.earth.danggeun.domain.DangMemberDTO;

public interface DangMemberDao {

	//이메일로 유저정보 가져오기
	DangMemberDTO select(String email) throws Exception;
	
}
