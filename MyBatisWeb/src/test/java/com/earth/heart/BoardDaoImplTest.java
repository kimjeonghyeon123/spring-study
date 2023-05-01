package com.earth.heart;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.earth.heart.dao.BoardDao;
import com.earth.heart.domain.BoardDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class BoardDaoImplTest {
	@Autowired
	private BoardDao boardDao;
	
	@Test
	public void countTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		BoardDTO boardDTO = new BoardDTO("Pioneering", "Ready for Action", "earth");
		
		assertTrue(boardDao.insert(boardDTO) == 1);
		assertTrue(boardDao.count() == 1);
		
		assertTrue(boardDao.insert(boardDTO) == 1);
		assertTrue(boardDao.count() == 2);
		
	}
	
	@Test
	public void insertTest() throws Exception {
		boardDao.deleteAll();
		BoardDTO boardDTO = new BoardDTO("Pioneering", "Ready for Action", "earth");
		assertTrue(boardDao.insert(boardDTO) == 1);
		
		boardDTO = new BoardDTO("Pioneering", "Ready for Action", "earth");
		assertTrue(boardDao.insert(boardDTO) == 1);
		assertTrue(boardDao.count() == 2);
		
		boardDao.deleteAll();
		boardDTO = new BoardDTO("Pioneering", "Ready for Action", "earth");
		assertTrue(boardDao.insert(boardDTO) == 1);
		assertTrue(boardDao.count() == 1);
	}
	
	@Test
	public void selectTest() throws Exception {
		assertTrue(boardDao != null);
		System.out.println("boardDao = " + boardDao);
		
		BoardDTO boardDTO = boardDao.select(6);
		System.out.println("boardDTO = " + boardDTO);
		assertTrue(boardDTO.getBno().equals(6));
		
		boardDao.deleteAll();
		boardDTO = new BoardDTO("Pioneering", "Ready for Action", "earth");
		boardDao.insert(boardDTO);
		
		boardDTO = boardDao.select(7);
		System.out.println("boardDTO = " + boardDTO);
		assertTrue(boardDTO.getBno().equals(7));
	}
	
	@Test
	public void selectAllTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		List<BoardDTO> list = boardDao.selectAll();
		assertTrue(list.size() == 0);
		
		BoardDTO boardDTO = new BoardDTO("Pioneering", "Ready for Action", "earth");
		assertTrue(boardDao.insert(boardDTO) == 1);
		
		list = boardDao.selectAll();
		assertTrue(list.size() == 1);
		
		assertTrue(boardDao.insert(boardDTO) == 1);
		list = boardDao.selectAll();
		assertTrue(list.size() == 2);
	} 
	
	@Test
	public void deleteTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		BoardDTO boardDTO = new BoardDTO("Pioneering", "Ready for Action", "earth");
		assertTrue(boardDao.insert(boardDTO) == 1);
		
		Integer bno = boardDao.selectAll().get(0).getBno();
		assertTrue(boardDao.delete(bno, boardDTO.getWriter()) == 1);
		assertTrue(boardDao.count() == 0);
		
		assertTrue(boardDao.insert(boardDTO) == 1);
		bno = boardDao.selectAll().get(0).getBno();
		assertTrue(boardDao.delete(bno, boardDTO.getWriter() + "ea") == 0);
		assertTrue(boardDao.count() == 1);
	}
	
	@Test
	public void deleteAllTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		BoardDTO boardDTO = new BoardDTO("Pioneering", "Ready for Action", "earth");
		assertTrue(boardDao.insert(boardDTO) == 1);
		assertTrue(boardDao.count() == 1);
		
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
	}
	
	@Test
	public void updateTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		BoardDTO boardDTO = new BoardDTO("Pioneering", "Ready for Action", "earth");
		assertTrue(boardDao.insert(boardDTO) == 1);
		
		Integer bno = boardDao.selectAll().get(0).getBno();
		System.out.println("bno = " + bno);
		
		boardDTO.setBno(bno);
		boardDTO.setTitle("yes i can");
		assertTrue(boardDao.update(boardDTO) == 1);
		
		BoardDTO boardDTO2 = boardDao.select(bno);
		assertTrue(boardDTO.equals(boardDTO2));
	}
}





