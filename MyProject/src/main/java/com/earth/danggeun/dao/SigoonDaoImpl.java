package com.earth.danggeun.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.danggeun.domain.SigoonDTO;

@Repository
public class SigoonDaoImpl implements SigoonDao {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.danggeun.dao.SigoonMapper.";
	
	@Override
	public int insert(SigoonDTO sigoonDTO) throws Exception {
		return session.insert(namespace + "insert", sigoonDTO);
	}

	@Override
	public List<SigoonDTO> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}

	@Override
	public List<SigoonDTO> selectBySido(String sido_code) throws Exception {
		return session.selectList(namespace + "selectBySido", sido_code);
	}

	@Override
	public SigoonDTO select(String sigoon_code) throws Exception {
		return session.selectOne(namespace + "select", sigoon_code);
	}

}
