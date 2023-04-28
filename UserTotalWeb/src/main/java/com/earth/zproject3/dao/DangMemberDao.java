package com.earth.zproject3.dao;

import com.earth.zproject3.domain.DangMember;

public interface DangMemberDao {
	DangMember selectDangMember(String email);
	int deleteDangMember(String email);
	int insertDangMember(DangMember dangMember);
	int updateDangMember(DangMember dangMember);
	void deleteAll();
}
