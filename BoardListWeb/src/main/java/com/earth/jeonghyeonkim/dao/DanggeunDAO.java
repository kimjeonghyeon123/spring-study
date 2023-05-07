package com.earth.jeonghyeonkim.dao;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;

public interface DanggeunDAO {
	
	DanggeunDTO select(Integer id) throws Exception;
	
	
}
