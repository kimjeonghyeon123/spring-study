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
	public List<DanggeunDTO> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	@Override
	public List<DanggeunDTO> selectByOption(Integer type_id) throws Exception {
		return session.selectList(namespace + "selectByOption", type_id);
	}
	@Override
	public DanggeunDTO select(Integer id) throws Exception {
		return session.selectOne(namespace + "select", id);
	}
	@Override
	public int increaseViewCnt(Integer id) throws Exception {
		return session.update(namespace + "increaseViewCnt", id);
	}
	@Override
	public int updateZzimCnt(Integer id, int cnt) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		map.put("cnt", cnt);
		return session.update(namespace + "updateZzimCnt", map);
	}
	@Override
	public int insert(DanggeunDTO danggeunDTO) throws Exception {
		return session.insert(namespace + "insert", danggeunDTO);
	}
	@Override
	public int delete(Integer id, String writer_email) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		map.put("writer_email", writer_email);
		return session.delete(namespace + "delete", map);
	}
	
}
