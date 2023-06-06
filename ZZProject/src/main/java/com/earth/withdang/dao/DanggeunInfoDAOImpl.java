package com.earth.withdang.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.DanggeunInfoDTO;
import com.earth.withdang.domain.DanggeunSearchItem;

@Repository
public class DanggeunInfoDAOImpl implements DanggeunInfoDAO {

	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.DanggeunInfoMapper.";
	
	public DanggeunInfoDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public int selectByOptionCnt(DanggeunSearchItem dsc) throws Exception {
		return session.selectOne(namespace + "selectByOptionCnt", dsc);
	}

	@Override
	public List<DanggeunInfoDTO> selectByOption(DanggeunSearchItem dsc) throws Exception {
		return session.selectList(namespace + "selectByOption", dsc);
	}

	@Override
	public DanggeunInfoDTO select(Integer id, String login_nickname) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		map.put("login_nickname", login_nickname);
		return session.selectOne(namespace + "select", map);
	}

	@Override
	public int update(DanggeunInfoDTO danggeunInfoDTO) throws Exception {
		return session.update(namespace + "update", danggeunInfoDTO);
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
		return session.selectOne(namespace + "updateZzimCnt", map);
	}

	@Override
	public int insert(DanggeunInfoDTO danggeunInfoDTO) throws Exception {
		return session.insert(namespace + "insert", danggeunInfoDTO);
	}

	@Override
	public int delete(Integer id) throws Exception {
		return session.delete(namespace + "delete", id);
	}
	
}
