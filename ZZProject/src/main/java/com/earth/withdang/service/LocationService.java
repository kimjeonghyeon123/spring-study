package com.earth.withdang.service;

import java.util.List;

import com.earth.withdang.domain.AddressDongDTO;
import com.earth.withdang.domain.AddressSidoDTO;
import com.earth.withdang.domain.AddressSigoonDTO;

public interface LocationService {

	List<AddressSidoDTO> readSido() throws Exception;
	
	List<AddressSigoonDTO> readSigoon(String sido_code) throws Exception;
	
	List<AddressDongDTO> readDong(String sigoon_code) throws Exception;
	
	AddressSidoDTO readSidoOne(String code) throws Exception;
	
	AddressSigoonDTO readSigoonOne(String code) throws Exception;
	
	AddressDongDTO readDongOne(String code) throws Exception;
	
}
