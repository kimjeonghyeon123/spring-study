package com.earth.withdang.dao;

import java.util.List;

import com.earth.withdang.domain.AddressDongDTO;

public interface AddressDongDAO {

	List<AddressDongDTO> selectBySigoon(String sigoon_code) throws Exception;
	
	AddressDongDTO select(String code) throws Exception;
	
}
