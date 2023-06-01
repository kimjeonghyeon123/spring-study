package com.earth.korea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.korea.dao.BoardDao;
import com.earth.korea.dao.CommentDao;
import com.earth.korea.domain.CommentDTO;

@Service
public class CommentServiceImpl implements CommentService {
	
	//두개의 dao가 모두 DI가 되어야 함
	//@Autowired 
	BoardDao boardDao;
	//@Autowired
	CommentDao commentDao;
	
	//@Autowired	//생성자주입으로 하는 것이 더 안전함 
	public CommentServiceImpl(BoardDao boardDao, CommentDao commentDao) {
		//super();
		this.boardDao = boardDao;
		this.commentDao = commentDao;
	}

	@Override
	public List<CommentDTO> getList(Integer bno) throws Exception {
		//throw new Exception("exception test");
		return commentDao.selectAll(bno);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int remove(Integer cno, Integer bno, String commenter) throws Exception {
		int rowCnt = boardDao.updateCommentCnt(bno, -1);
		rowCnt = commentDao.delete(cno, commenter);
		return rowCnt;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int write(CommentDTO commentDTO) throws Exception {
		boardDao.updateCommentCnt(commentDTO.getBno(), 1);
		return commentDao.insert(commentDTO);
	}

	@Override
	public int modify(CommentDTO commentDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentDao.update(commentDTO);
	}

}























