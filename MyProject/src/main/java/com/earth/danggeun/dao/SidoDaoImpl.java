package com.earth.danggeun.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.danggeun.domain.SidoDTO;

@Repository
public class SidoDaoImpl implements SidoDao {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.danggeun.dao.SidoMapper.";
	
	@Override
	public int insert(SidoDTO sidoDTO) throws Exception {
		return session.insert(namespace + "insert", sidoDTO);
	}

	@Override
	public List<SidoDTO> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}

	@Override
	public SidoDTO select(String sido_code) throws Exception {
		return session.selectOne(namespace + "select", sido_code);
	}
	
}
