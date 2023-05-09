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
	public List<DanggeunDTO> selectByType(Integer type_id) throws Exception {
		return session.selectList(namespace + "selectByType", type_id);
	}

	@Override
	public int insert(DanggeunDTO danggeunDTO) throws Exception {
		return session.insert(namespace + "insert", danggeunDTO);
	}

	@Override
	public int delete(Integer id, String writer) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		map.put("writer", writer);
		return session.delete(namespace + "delete", map);
	}

	@Override
	public int addViewCnt(Integer id) throws Exception {
		return session.update(namespace + "addViewCnt", id);
	}

	@Override
	public int addAddCnt(Integer id) throws Exception {
		return session.selectOne(namespace + "addAddCnt", id);
	}

	@Override
	public int insertStore(Integer id, String writer) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		map.put("writer", writer);
		return session.insert(namespace + "insertStore", map);
	}
	
	
	
}
