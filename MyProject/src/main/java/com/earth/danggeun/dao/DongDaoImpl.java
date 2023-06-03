package com.earth.danggeun.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.danggeun.domain.DongDTO;

@Repository
public class DongDaoImpl implements DongDao {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.danggeun.dao.DongMapper.";
	
	@Override
	public int insert(DongDTO dongDTO) throws Exception {
		return session.insert(namespace + "insert", dongDTO);
	}

	@Override
	public List<DongDTO> selectBySigoon(String sigoon_code) throws Exception {
		return session.selectList(namespace + "selectBySigoon", sigoon_code);
	}

	@Override
	public DongDTO select(String dong_code) throws Exception {
		return session.selectOne(namespace + "select", dong_code);
	}
	
}
