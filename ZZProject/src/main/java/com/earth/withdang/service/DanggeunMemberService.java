package com.earth.withdang.service;

import com.earth.withdang.domain.DanggeunMemberDTO;

public interface DanggeunMemberService {

	DanggeunMemberDTO read(String user_email) throws Exception;
	
}
