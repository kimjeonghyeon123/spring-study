package com.earth.danggeun.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.danggeun.domain.ZzimDanggeunDTO;

@Repository
public class ZzimDanggeunDaoImpl implements ZzimDanggeunDao {

	private SqlSession session;
	private static String namespace = "com.earth.danggeun.dao.ZzimDanggeunMapper.";
	
	public ZzimDanggeunDaoImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ZzimDanggeunDTO> selectByMemberEmail(String email) throws Exception {
		return session.selectList(namespace + "selectByMemberEmail", email);
	}
	@Override
	public List<ZzimDanggeunDTO> selectByDanggeunId(Integer id) throws Exception {
		return session.selectList(namespace + "selectByDanggeunId", id);
	}
	@Override
	public int insert(ZzimDanggeunDTO zzimDanggeunDto) throws Exception {
		return session.insert(namespace + "insert", zzimDanggeunDto);
	}
	@Override
	public int deleteAllByMemberEmail(String email) throws Exception {
		return session.delete(namespace + "deleteAllByMemberEmail", email);
	}
	@Override
	public int deleteAllByDanggeunId(Integer id) throws Exception {
		return session.delete(namespace + "deleteAllByDanggeunId", id);
	}
	@Override
	public int delete(ZzimDanggeunDTO zzimDanggeunDto) throws Exception {
		return session.delete(namespace + "delete", zzimDanggeunDto);
	}
	
}
