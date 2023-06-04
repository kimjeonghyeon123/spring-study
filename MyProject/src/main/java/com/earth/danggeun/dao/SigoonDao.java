package com.earth.danggeun.dao;

import java.util.List;

import com.earth.danggeun.domain.SigoonDTO;

public interface SigoonDao {

	int insert(SigoonDTO gugoonDTO) throws Exception;
	
	List<SigoonDTO> selectAll() throws Exception;
	
	List<SigoonDTO> selectBySido(String sido_code) throws Exception;
	
	SigoonDTO select(String sigoon_code) throws Exception;
	
}
