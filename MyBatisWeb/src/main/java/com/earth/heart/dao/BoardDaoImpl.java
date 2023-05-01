package com.earth.heart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.heart.domain.BoardDTO;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.heart.dao.BoardMapper.";
			
	@Override
	public BoardDTO select(Integer bno) throws Exception {
		return session.selectOne(namespace + "select", bno);
	}

	@Override
	public List<BoardDTO> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public int delete(Integer bno, String writer) throws Exception { 
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("writer", writer);
		
		return session.delete(namespace + "delete", map);
	}
	
	@Override
	public int deleteAll() throws Exception {
		return session.delete(namespace + "deleteAll");
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		return session.insert(namespace + "insert", boardDTO);
	}

	@Override
	public int count() throws Exception {
		return session.selectOne(namespace + "count");
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return session.update(namespace + "update", boardDTO);
	}

	
}
