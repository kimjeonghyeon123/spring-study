package com.earth.jeonghyeonkim.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.jeonghyeonkim.domain.DanggeunTypeDTO;

@Repository
public class DanggeunTypeDAOImpl implements DanggeunTypeDAO {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.jeonghyeonkim.dao.DanggeunTypeMapper.";
	
	@Override
	public List<DanggeunTypeDTO> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}

}
