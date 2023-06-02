package com.earth.jeonghyeonkim.dao;

import java.util.List;

import com.earth.jeonghyeonkim.domain.DanggeunTypeDTO;

public interface DanggeunTypeDAO {
	
	//타입 목록을 부를 때 사용
	List<DanggeunTypeDTO> selectAll() throws Exception;
	
}
