package com.earth.jeonghyeonkim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earth.jeonghyeonkim.dao.DangMemberDAO;
import com.earth.jeonghyeonkim.domain.DangMemberDTO;

@Service
public class DangMemberServiceImpl implements DangMemberService {

	@Autowired
	DangMemberDAO dangMemberDAO;
	
	@Override
	public DangMemberDTO getDangMember(String email) throws Exception {
		return dangMemberDAO.select(email);
	}

	@Override
	public int registerDangMember(DangMemberDTO dangMemberDTO) throws Exception {
		return dangMemberDAO.insert(dangMemberDTO);
	}
	
}
