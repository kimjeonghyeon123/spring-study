package com.earth.jeonghyeonkim.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.jeonghyeonkim.domain.ZzimDanggeunDTO;

@Repository
public class ZzimDanggeunDAOImpl implements ZzimDanggeunDAO {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.jeonghyeonkim.dao.ZzimDanggeunMapper.";

	@Override
	public int selectcount(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception {
		return session.selectOne(namespace + "selectcount", zzimDanggeunDTO);
	}
	
	@Override
	public List<Integer> selectByMemberEmail(String member_email) throws Exception {
		return session.selectList(namespace + "selectByMemberEmail", member_email);
	}

	@Override
	public List<String> selectByMemberEmail(Integer danggeun_id) throws Exception {
		return session.selectList(namespace + "selectByMemberEmail", danggeun_id);
	}

	@Override
	public int deleteAllByDanggeunId(Integer danggeun_id) throws Exception {
		return session.delete(namespace + "deleteAllByDanggeunId", danggeun_id);
	}

	@Override
	public int deleteAllByMemberEmail(String member_email) throws Exception {
		return session.delete(namespace + "deleteAllByMemberEmail", member_email);
	}

	@Override
	public int delete(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception {
		return session.delete(namespace + "delete", zzimDanggeunDTO);
	}

	@Override
	public int insert(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception {
		return session.insert(namespace + "insert", zzimDanggeunDTO);
	}
	
}
