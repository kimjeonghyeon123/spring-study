package com.earth.jeonghyeonkim.dao;

import java.util.List;
import java.util.Map;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;

public interface DanggeunDAO {
	
	DanggeunDTO select(Integer id) throws Exception;
	List<DanggeunDTO> selectAll() throws Exception;
	List<DanggeunDTO> selectBySearch(Integer type_id, Integer local_id) throws Exception;
	int insert(DanggeunDTO danggeunDTO) throws Exception;
	
}
