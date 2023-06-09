package com.earth.persistence;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.earth.mapper.TimeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TimeMapperTest {
	
	@Autowired
	private TimeMapper timeMapper;
	@Ignore
	@Test
	public void testGetTime() {
		System.out.println(timeMapper.getTime1());
	}
	@Ignore
	@Test
	public void testGetTime2() {
		System.out.println(timeMapper.getTime2());
	}
}
