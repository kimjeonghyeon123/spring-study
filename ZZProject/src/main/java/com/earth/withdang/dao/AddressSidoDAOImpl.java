package com.earth.withdang.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.AddressSidoDTO;

@Repository
public class AddressSidoDAOImpl implements AddressSidoDAO {

	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.AddressSidoMapper.";
	
	public AddressSidoDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<AddressSidoDTO> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}

	@Override
	public AddressSidoDTO select(String code) throws Exception {
		return session.selectOne(namespace + "select", code);
	}

	
	
}
