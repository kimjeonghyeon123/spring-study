package com.earth.withdang.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.DanggeunMemberDTO;

@Repository
public class DanggeunMemberDAOImpl implements DanggeunMemberDAO {

	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.DanggeunMemberMapper.";
	
	public DanggeunMemberDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public DanggeunMemberDTO select(String user_email) throws Exception {
		return session.selectOne(namespace + "select", user_email);
	}

}
