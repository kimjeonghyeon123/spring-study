package com.earth.danggeun.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.danggeun.domain.DanggeunTypeDTO;

@Repository
public class DanggeunTypeDaoImpl implements DanggeunTypeDao {

	private SqlSession session;
	private static String namespace = "com.earth.danggeun.dao.DanggeunTypeMapper.";
	
	public DanggeunTypeDaoImpl(SqlSession session) {
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
