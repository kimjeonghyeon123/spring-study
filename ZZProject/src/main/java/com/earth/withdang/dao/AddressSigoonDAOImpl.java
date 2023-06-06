package com.earth.withdang.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.AddressSigoonDTO;

@Repository
public class AddressSigoonDAOImpl implements AddressSigoonDAO {
	
	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.AddressSigoonMapper.";
	
	public AddressSigoonDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<AddressSigoonDTO> selectBySido(String sido_code) throws Exception {
		return session.selectList(namespace + "selectBySido", sido_code);
	}

	@Override
	public AddressSigoonDTO select(String code) throws Exception {
		return session.selectOne(namespace + "select", code);
	}
}
