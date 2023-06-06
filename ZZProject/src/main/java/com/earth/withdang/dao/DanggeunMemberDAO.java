package com.earth.withdang.dao;

import com.earth.withdang.domain.DanggeunMemberDTO;

public interface DanggeunMemberDAO {

	DanggeunMemberDTO select(String user_email) throws Exception;
	
}
