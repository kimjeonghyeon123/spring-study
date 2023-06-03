package com.earth.danggeun.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.danggeun.domain.DangMemberDTO;

@Repository
public class DangMemberDaoImpl implements DangMemberDao {

	private SqlSession session;
	private static String namespace = "com.earth.danggeun.dao.DangMemberMapper.";
	
	public DangMemberDaoImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public DangMemberDTO select(String email) throws Exception {
		return session.selectOne(namespace + "select", email);
	}

}
