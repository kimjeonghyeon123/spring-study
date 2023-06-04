package com.earth.danggeun.service;

import java.util.List;

import com.earth.danggeun.domain.DongDTO;
import com.earth.danggeun.domain.SidoDTO;
import com.earth.danggeun.domain.SigoonDTO;

public interface LocationService {

	List<SidoDTO> readSido() throws Exception;
	
	List<SigoonDTO> readSigoon(String sido_code) throws Exception;
	
	List<DongDTO> readDong(String sigoon_code) throws Exception;
	
	SidoDTO readSidoOne(String sido_code) throws Exception;
	
	SigoonDTO readSigoonOne(String sigoon_code) throws Exception;
	
	DongDTO readDongOne(String dong_code) throws Exception;
	
}
