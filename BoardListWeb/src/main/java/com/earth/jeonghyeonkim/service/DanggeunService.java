package com.earth.jeonghyeonkim.service;

import java.util.List;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;

public interface DanggeunService {
	
	DanggeunDTO read(Integer id) throws Exception;
	List<DanggeunDTO> readAll() throws Exception;
	List<DanggeunDTO> searchItems(Integer type_id, Integer local_id) throws Exception;
	int register(DanggeunDTO danggeunDTO) throws Exception;
	
}
