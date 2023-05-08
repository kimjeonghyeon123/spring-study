package com.earth.jeonghyeonkim.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;

@Repository
public class DanggeunDAOImpl implements DanggeunDAO {
	
	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.jeonghyeonkim.dao.DanggeunMapper.";
	
	@Override
	public DanggeunDTO select(Integer id) throws Exception {
		return session.selectOne(namespace + "select", id);
	}

	@Override
	public List<DanggeunDTO> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}

	@Override
	public List<DanggeunDTO> selectBySearch(Integer type_id, Integer local_id) throws Exception {
		Map map = new HashMap();
		map.put("type_id", type_id);
		map.put("local_id", local_id);
		return session.selectList(namespace + "selectBySearch", map);
	}

	@Override
	public int insert(DanggeunDTO danggeunDTO) throws Exception {
		return session.insert(namespace + "insert", danggeunDTO);
	}
	
	
	
}
