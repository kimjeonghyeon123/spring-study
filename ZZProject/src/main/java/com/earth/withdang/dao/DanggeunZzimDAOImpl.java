package com.earth.withdang.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.DanggeunZzimDTO;

@Repository
public class DanggeunZzimDAOImpl implements DanggeunZzimDAO {
	
	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.DanggeunZzimMapper.";
	
	public DanggeunZzimDAOImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<DanggeunZzimDTO> selectByUserNickname(String user_nickname) throws Exception {
		return session.selectList(namespace + "selectByUserNickname", user_nickname);
	}

	@Override
	public List<DanggeunZzimDTO> selectByDanggeunId(Integer danggeun_id) throws Exception {
		return session.selectList(namespace + "selectByDanggeunId", danggeun_id);
	}

	@Override
	public int insert(DanggeunZzimDTO DanggeunZzimDTO) throws Exception {
		return session.insert(namespace + "insert", DanggeunZzimDTO);
	}

	@Override
	public int deleteAllByUserNickname(String user_nickname) throws Exception {
		return session.delete(namespace + "deleteAllByUserNickname", user_nickname);
	}

	@Override
	public int delete(DanggeunZzimDTO DanggeunZzimDTO) throws Exception {
		return session.delete(namespace + "delete", DanggeunZzimDTO);
	}

}
