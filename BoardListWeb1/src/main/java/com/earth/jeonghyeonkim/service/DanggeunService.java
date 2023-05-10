package com.earth.jeonghyeonkim.service;

import java.util.List;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;

public interface DanggeunService {
	
	DanggeunDTO read(Integer id) throws Exception;
	List<DanggeunDTO> readAll() throws Exception;
	List<DanggeunDTO> searchItems(Integer type_id) throws Exception;
	int register(DanggeunDTO danggeunDTO) throws Exception;
	int storeProduct(Integer id, String writer) throws Exception;
	int addCount(Integer id) throws Exception;
	List<Integer> getStoreid(String writer) throws Exception;
	
}
