package com.earth.korea;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.earth.korea.dao.BoardDao;
import com.earth.korea.dao.CommentDao;
import com.earth.korea.domain.BoardDTO;
import com.earth.korea.domain.CommentDTO;
import com.earth.korea.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommentServiceImplTest {
	
	@Autowired CommentService commentService;
	@Autowired CommentDao commentDao;
	@Autowired BoardDao boardDao;
	
	//@Test
	public void remove() throws Exception {
		boardDao.deleteAll();
		
		BoardDTO boardDTO = new BoardDTO("Pioneering", "취업준비", "earth");
		assertTrue(boardDao.insert(boardDTO)==1);
		Integer bno = boardDao.selectAll().get(0).getBno();
		System.out.println("bno = " + bno);
		
		commentDao.deleteAll(bno);
		CommentDTO commentDTO = new CommentDTO(bno, 0, "hi", "earth1");
		assertTrue(boardDao.select(bno).getComment_cnt() == 0);
		assertTrue(commentService.write(commentDTO)==1); //댓글 작성함 
		assertTrue(boardDao.select(bno).getComment_cnt()==1);
		
		Integer cno = commentDao.selectAll(bno).get(0).getCno();
		
		int rowCnt = commentService.remove(cno, bno, commentDTO.getCommenter());
		assertTrue(rowCnt==1);
		assertTrue(boardDao.select(bno).getComment_cnt() == 0);
	}
	
	@Test
	public void write() throws Exception {
		boardDao.deleteAll();
		BoardDTO boardDTO = new BoardDTO("Pioneering", "취업준비", "earth");
		assertTrue(boardDao.insert(boardDTO) == 1);
		Integer bno = boardDao.selectAll().get(0).getBno();
		
		commentDao.deleteAll(bno);
		CommentDTO commentDTO = new CommentDTO(bno, 0, "hi", "earth1");
		
		assertTrue(boardDao.select(bno).getComment_cnt() == 0);
		assertTrue(commentService.write(commentDTO)==1);
		
		Integer cno = commentDao.selectAll(bno).get(0).getCno();
		assertTrue(boardDao.select(bno).getComment_cnt() == 1);
	}

}




































