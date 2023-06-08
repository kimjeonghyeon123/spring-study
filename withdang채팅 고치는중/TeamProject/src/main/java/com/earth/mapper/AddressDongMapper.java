package com.earth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.AddressDongDTO;

@Mapper
public interface AddressDongMapper {

	List<AddressDongDTO> selectBySigoon(String sigoon_code) throws Exception;
	
	AddressDongDTO select(String code) throws Exception;
	
}
