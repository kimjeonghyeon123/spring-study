package com.earth.jeonghyeonkim;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.earth.jeonghyeonkim.dao.DangBoardDAO;
import com.earth.jeonghyeonkim.domain.DangBoardDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class BoardTest {

	@Autowired
	private DangBoardDAO dangBoardDAO;
	
	@Test
	public void insertDummyTestData() throws Exception {
		dangBoardDAO.deleteAll();
		
		for(int i = 1; i <= 250; i++) {
			DangBoardDTO dangBoardDTO = new DangBoardDTO("Pioneering" + i, "Ready For Action", "earth@naver.com");
			dangBoardDAO.insert(dangBoardDTO);
		}
		
	}

}
