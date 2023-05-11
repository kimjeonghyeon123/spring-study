package com.earth.jeonghyeonkim.service;

import java.util.List;

import com.earth.jeonghyeonkim.domain.DanggeunTypeDTO;

public interface DanggeunTypeService {
	
	List<DanggeunTypeDTO> getTypeList() throws Exception;
	
}
