package com.earth.withdang.dao;

import java.util.List;

import com.earth.withdang.domain.DanggeunTypeDTO;

public interface DanggeunTypeDAO {

	//타입 목록을 부를 때 사용
	List<DanggeunTypeDTO> selectAll() throws Exception;
	
	//타입 하나 가져올 때 사용
	DanggeunTypeDTO select(Integer id) throws Exception;
	
}
