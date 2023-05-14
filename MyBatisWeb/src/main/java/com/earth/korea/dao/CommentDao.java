package com.earth.korea.dao;

import java.util.List;

import com.earth.korea.domain.CommentDTO;

public interface CommentDao {
	int count(Integer bno) throws Exception;
	int deleteAll(Integer bno) throws Exception;
	int delete(Integer cno, String commenter) throws Exception;
	List<CommentDTO> selectAll(Integer bno) throws Exception;
	int insert(CommentDTO commentDTO) throws Exception;
	int update(CommentDTO commentDTO) throws Exception;
	
}
