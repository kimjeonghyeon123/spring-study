package com.earth.danggeun.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.danggeun.domain.DanggeunDTO;
import com.earth.danggeun.domain.DanggeunSearchItem;

@Repository
public class DanggeunDaoImpl implements DanggeunDao {

	private SqlSession session;
	private static String namespace = "com.earth.danggeun.dao.DanggeunMapper.";
	
	public DanggeunDaoImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public int selectByOptionCnt(DanggeunSearchItem dsc) throws Exception {
		return session.selectOne(namespace + "selectByOptionCnt", dsc);
	}

	@Override
	public List<DanggeunDTO> selectByOption(DanggeunSearchItem dsc) throws Exception {
		return session.selectList(namespace + "selectByOption", dsc);
	}

	@Override
	public DanggeunDTO select(Integer id, String login_email) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		map.put("login_email", login_email);
		return session.selectOne(namespace + "select", map);
	}

	@Override
	public int update(DanggeunDTO danggeunDTO) throws Exception {
		return session.update(namespace + "update", danggeunDTO);
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
	public int delete(Integer id) throws Exception {	
		return session.delete(namespace + "delete", id);
	}

}
