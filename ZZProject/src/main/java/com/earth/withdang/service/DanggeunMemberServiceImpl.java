package com.earth.withdang.service;

import org.springframework.stereotype.Service;

import com.earth.withdang.dao.DanggeunMemberDAO;
import com.earth.withdang.domain.DanggeunMemberDTO;

@Service
public class DanggeunMemberServiceImpl implements DanggeunMemberService {

	DanggeunMemberDAO danggeunMemberDAO;
	
	public DanggeunMemberServiceImpl(DanggeunMemberDAO danggeunMemberDAO) {
		this.danggeunMemberDAO = danggeunMemberDAO;
	}

	@Override
	public DanggeunMemberDTO read(String user_email) throws Exception {
		return danggeunMemberDAO.select(user_email);
	}

}
