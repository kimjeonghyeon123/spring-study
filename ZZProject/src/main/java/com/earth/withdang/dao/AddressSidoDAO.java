package com.earth.withdang.dao;

import java.util.List;

import com.earth.withdang.domain.AddressSidoDTO;

public interface AddressSidoDAO {

	List<AddressSidoDTO> selectAll() throws Exception;
	
	AddressSidoDTO select(String code) throws Exception;
	
}
