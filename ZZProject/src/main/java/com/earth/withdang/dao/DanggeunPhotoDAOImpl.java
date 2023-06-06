package com.earth.withdang.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.DanggeunPhotoDTO;

@Repository
public class DanggeunPhotoDAOImpl implements DanggeunPhotoDAO {

	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.DanggeunPhotoMapper.";
	
	public DanggeunPhotoDAOImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<DanggeunPhotoDTO> selectAllByDanggeunId(Integer danggeun_id) throws Exception {
		return session.selectList(namespace + "selectAllByDanggeunId", danggeun_id);
	}

	@Override
	public int insert(DanggeunPhotoDTO danggeunPhotoDTO) throws Exception {
		return session.insert(namespace + "insert", danggeunPhotoDTO);
	}

	@Override
	public int deleteAll(Integer danggeun_id) throws Exception {
		return session.delete(namespace + "deleteAll", danggeun_id);
	}

}
