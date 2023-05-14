package com.earth.korea;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.earth.korea.dao.CommentDao;
import com.earth.korea.domain.CommentDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommentDaoImplTest {

	@Autowired
	CommentDao commentDao;
	
	//@Test
	public void count() throws Exception {
		commentDao.deleteAll(0);
		assertTrue(commentDao.count(0)==0);
	}
	
	//@Test
	public void delete() throws Exception {
		commentDao.deleteAll(1);
		CommentDTO commentDTO = new CommentDTO(1, 0, "힘을 내라!", "earth");
		assertTrue(commentDao.insert(commentDTO)==1);
		assertTrue(commentDao.count(1)==1);
	}
	
	//@Test
	public void insert() throws Exception {
		commentDao.deleteAll(1);
		CommentDTO commentDTO = new CommentDTO(1, 0, "힘을 내라!", "earth");
		assertTrue(commentDao.insert(commentDTO)==1);
		assertTrue(commentDao.count(1)==1);	
		
		commentDTO = new CommentDTO(1, 0, "힘을 더 내라!", "earth1");
		assertTrue(commentDao.insert(commentDTO)==1);
		assertTrue(commentDao.count(1)==2);	
	}
	
	//@Test
	public void selectAll() throws Exception {
		commentDao.deleteAll(1);
		CommentDTO commentDTO = new CommentDTO(1, 0, "힘을 내라!", "earth");
		assertTrue(commentDao.insert(commentDTO)==1);
		assertTrue(commentDao.count(1)==1);	
		
		List<CommentDTO> list = commentDao.selectAll(1);
		assertTrue(list.size()==1);
		
		commentDTO = new CommentDTO(1, 0, "힘을 더 내라!", "earth2");
		assertTrue(commentDao.insert(commentDTO)==1);
		assertTrue(commentDao.count(1)==2);
		
		list = commentDao.selectAll(1);
		assertTrue(list.size()==2);
		
	}
	
	//@Test
	public void select() throws Exception {
		commentDao.deleteAll(1);
		CommentDTO commentDTO = new CommentDTO(1, 0, "힘을 내라!", "earth");
		assertTrue(commentDao.insert(commentDTO)==1);
		assertTrue(commentDao.count(1)==1);	
		
		List<CommentDTO> list = commentDao.selectAll(1);
		String comment = list.get(0).getComment();
		String commenter = list.get(0).getCommenter();
		assertTrue(comment.equals(commentDTO.getComment()));
		assertTrue(commenter.equals(commentDTO.getCommenter()));
	}
	
	@Test
	public void update() throws Exception {
		commentDao.deleteAll(1);
		CommentDTO commentDTO = new CommentDTO(1, 0, "힘을 내라!", "earth");
		assertTrue(commentDao.insert(commentDTO)==1);
		assertTrue(commentDao.count(1)==1);
		
		List<CommentDTO> list = commentDao.selectAll(1);
		commentDTO.setCno(list.get(0).getCno());
		commentDTO.setComment("힘을 더더 내라!");
		assertTrue(commentDao.update(commentDTO)==1);
		
		list = commentDao.selectAll(1);
		String comment = list.get(0).getComment();
		String commenter = list.get(0).getCommenter();
		assertTrue(comment.equals(commentDTO.getComment()));
		assertTrue(commenter.equals(commentDTO.getCommenter()));
	}
}



















