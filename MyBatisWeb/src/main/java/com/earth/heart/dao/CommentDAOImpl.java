package com.earth.heart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.heart.domain.CommentDTO;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.heart.dao.CommentMapper.";

	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(namespace + "count", bno);
	}
	
	@Override
	public List<CommentDTO> selectAll(Integer bno) throws Exception {
		return session.selectList(namespace + "selectAll", bno);
	}

	@Override
	public int deleteAll(Integer bno) throws Exception {
		return session.delete(namespace + "deleteAll", bno);
	}

	@Override
	public int delete(Integer cno, String commenter) throws Exception {
		Map map = new HashMap();
		map.put("cno", cno);
		map.put("commenter", commenter);
		return session.delete(namespace + "delete", map);
	}

	@Override
	public int insert(CommentDTO commentDTO) throws Exception {
		return session.insert(namespace + "insert", commentDTO);
	}

	@Override
	public int update(CommentDTO commentDTO) throws Exception {
		return session.update(namespace + "update", commentDTO);
	}

}
