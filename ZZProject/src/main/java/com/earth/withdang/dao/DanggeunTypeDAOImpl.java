package com.earth.withdang.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.DanggeunTypeDTO;

@Repository
public class DanggeunTypeDAOImpl implements DanggeunTypeDAO {

	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.DanggeunTypeMapper.";
	
	public DanggeunTypeDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<DanggeunTypeDTO> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}

	@Override
	public DanggeunTypeDTO select(Integer id) throws Exception {
		return session.selectOne(namespace + "select", id);
	}

}
