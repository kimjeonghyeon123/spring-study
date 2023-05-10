package com.earth.jeonghyeonkim;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.earth.jeonghyeonkim.dao.DanggeunDAO;
import com.earth.jeonghyeonkim.domain.DanggeunDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class DanggeunTest {
	
	@Autowired
	DanggeunDAO danggeunDAO;
	
	@Test
	public void insertTest() throws Exception {
		for(int i=1; i <= 20; i++) {
			DanggeunDTO danggeunDTO = new DanggeunDTO("hi" + i, "prdt" + i, 1, 1, 3000, "hihi", "gojhkim123@naver.com");
			danggeunDAO.insert(danggeunDTO);
		}
	}
	
}