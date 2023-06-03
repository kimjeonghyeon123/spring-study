package com.earth.danggeun.dao;

import java.util.List;

import com.earth.danggeun.domain.DanggeunTypeDTO;

public interface DanggeunTypeDao {
	
	//타입 목록을 부를 때 사용
	List<DanggeunTypeDTO> selectAll() throws Exception;
	
}
