package com.earth.heart.dao;

import java.util.List;

import com.earth.heart.domain.CommentDTO;

public interface CommentDAO {

	int count(Integer bno) throws Exception;	
	List<CommentDTO> selectAll(Integer bno) throws Exception;
	int deleteAll(Integer bno) throws Exception;
	int delete(Integer cno, String commenter) throws Exception;
	int insert(CommentDTO commentDTO) throws Exception;
	int update(CommentDTO commentDTO) throws Exception;
	
}
