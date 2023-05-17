package com.earth.heart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.heart.dao.BoardDao;
import com.earth.heart.dao.CommentDAO;
import com.earth.heart.domain.BoardDTO;
import com.earth.heart.domain.CommentDTO;

@Service
public class CommentServiceImpl implements CommentService {

//	@Autowired
	private CommentDAO commentDAO;	
//	@Autowired
	private BoardDao boardDAO;
	
	public CommentServiceImpl(CommentDAO commentDAO, BoardDao boardDAO) {
		this.commentDAO = commentDAO;
		this.boardDAO = boardDAO;
	}
	
	@Override
	public List<CommentDTO> getList(Integer bno) throws Exception {
		return commentDAO.selectAll(bno);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int remove(Integer cno, Integer bno, String Commenter) throws Exception {
		int rowCnt = boardDAO.updateCommentCnt(bno, -1);
		rowCnt = commentDAO.delete(cno, Commenter);
		return rowCnt;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int write(CommentDTO commentDTO) throws Exception {
		int rowCnt = boardDAO.updateCommentCnt(commentDTO.getBno(), 1);
		rowCnt = commentDAO.insert(commentDTO);
		return rowCnt;
	}

	@Override
	public int modify(CommentDTO commentDTO) throws Exception {
		return commentDAO.update(commentDTO);
	}

}
