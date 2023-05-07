package com.earth.jeonghyeonkim.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;

@Repository
public class DanggeunDAOImpl implements DanggeunDAO {
	
	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.jeonghyeonkim.dao.DanggeunMapper";
	
	@Override
	public DanggeunDTO select(Integer id) throws Exception {
		return session.selectOne(namespace + "select", id);
	}
	
	
	
}
