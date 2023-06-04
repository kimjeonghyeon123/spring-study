package com.earth.danggeun.service;

import org.springframework.stereotype.Service;

import com.earth.danggeun.dao.DangMemberDao;
import com.earth.danggeun.domain.DangMemberDTO;

@Service
public class DangMemberServiceImpl implements DangMemberService {

	private DangMemberDao dangMemberDao;
	
	public DangMemberServiceImpl(DangMemberDao dangMemberDao) {
		this.dangMemberDao = dangMemberDao;
	}

	@Override
	public DangMemberDTO readDangMember(String email) throws Exception {
		return dangMemberDao.select(email);
	}
	
}
