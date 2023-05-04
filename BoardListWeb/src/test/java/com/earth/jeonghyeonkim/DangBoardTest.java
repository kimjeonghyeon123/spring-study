package com.earth.jeonghyeonkim;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.earth.jeonghyeonkim.dao.DangBoardDAO;
import com.earth.jeonghyeonkim.domain.DangBoardDTO;
import com.earth.jeonghyeonkim.service.DangBoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class DangBoardTest {

	@Autowired
	DangBoardService dangBoardService;
	
	@Test
	public void makeBoardList() throws Exception {
		for(int i=1; i<=250; i++) {
			DangBoardDTO dangBoardDTO = new DangBoardDTO("hi" + i, "kim", "1234");
			dangBoardService.write(dangBoardDTO);
		}
	}
}
