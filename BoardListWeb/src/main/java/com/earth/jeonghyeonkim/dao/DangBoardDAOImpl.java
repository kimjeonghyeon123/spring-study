package com.earth.jeonghyeonkim.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.jeonghyeonkim.domain.DangBoardDTO;
import com.earth.jeonghyeonkim.domain.DangMemberDTO;
import com.earth.jeonghyeonkim.domain.SearchItem;

@Repository
public class DangBoardDAOImpl implements DangBoardDAO {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.jeonghyeonkim.dao.DangBoardMapper.";
	
	@Override
	public DangBoardDTO select(Integer bno) throws Exception {
		return session.selectOne(namespace + "select", bno);
	}

	@Override
	public List<DangBoardDTO> selectPage(Map map) throws Exception {
		return session.selectList(namespace + "selectPage", map);
	}

	@Override
	public List<DangBoardDTO> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}

	@Override
	public int increaseViewCnt(Integer bno) throws Exception {
		return session.update(namespace + "increaseViewCnt", bno);
	}

	@Override
	public int count() throws Exception {
		return session.selectOne(namespace + "count");
	}

	@Override
	public int searchResultCnt(SearchItem sc) throws Exception {
		return session.selectOne(namespace + "searchResultCnt", sc);
	}

	@Override
	public List<DangBoardDTO> searchSelectPage(SearchItem sc) throws Exception {
		return session.selectList(namespace + "searchSelectPage", sc);
	}

	@Override
	public int insert(DangBoardDTO dangBoardDTO) throws Exception {
		return session.insert(namespace + "insert", dangBoardDTO);
	}

}
