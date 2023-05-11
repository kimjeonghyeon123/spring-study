package com.earth.jeonghyeonkim.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.jeonghyeonkim.domain.DangMemberDTO;

@Repository
public class DangMemberDAOImpl implements DangMemberDAO {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.jeonghyeonkim.dao.DangMemberMapper.";
	
	@Override
	public DangMemberDTO select(String email) throws Exception {
		return session.selectOne(namespace + "select", email);
	}

	@Override
	public int insert(DangMemberDTO dangMemberDTO) throws Exception {
		return session.insert(namespace + "insert", dangMemberDTO);
	}

	@Override
	public String selectMemberName(String email) throws Exception {
		return session.selectOne(namespace + "selectMemberName", email);
	}

}
