package com.earth.withdang.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.AddressDongDTO;

@Repository
public class AddressDongDAOImpl implements AddressDongDAO {
	
	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.AddressDongMapper.";
	
	public AddressDongDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<AddressDongDTO> selectBySigoon(String sigoon_code) throws Exception {
		return session.selectList(namespace + "selectBySigoon", sigoon_code);
	}

	@Override
	public AddressDongDTO select(String code) throws Exception {
		return session.selectOne(namespace + "select", code);
	}
}
