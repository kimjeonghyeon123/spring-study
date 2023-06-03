package com.earth.danggeun.dao;

import java.util.List;

import com.earth.danggeun.domain.DongDTO;

public interface DongDao {

	int insert(DongDTO dongDTO) throws Exception;
		
	List<DongDTO> selectBySigoon(String sigoon_code) throws Exception;
	
	DongDTO select(String dong_code) throws Exception;
	
}
