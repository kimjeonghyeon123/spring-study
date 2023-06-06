package com.earth.withdang.dao;

import java.util.List;

import com.earth.withdang.domain.AddressSigoonDTO;

public interface AddressSigoonDAO {

	List<AddressSigoonDTO> selectBySido(String sido_code) throws Exception;
	
	AddressSigoonDTO select(String code) throws Exception;
	
}
