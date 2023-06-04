package com.earth.danggeun.dao;

import java.util.List;

import com.earth.danggeun.domain.SidoDTO;

public interface SidoDao {

	int insert(SidoDTO sidoDTO) throws Exception;
	
	List<SidoDTO> selectAll() throws Exception;
	
	SidoDTO select(String sido_code) throws Exception;
	
}
