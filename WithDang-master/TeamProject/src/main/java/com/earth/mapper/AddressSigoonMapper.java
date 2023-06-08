package com.earth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.AddressSigoonDTO;

@Mapper
public interface AddressSigoonMapper {

	List<AddressSigoonDTO> selectBySido(String sido_code) throws Exception;
	
	AddressSigoonDTO select(String code) throws Exception;
	
}
