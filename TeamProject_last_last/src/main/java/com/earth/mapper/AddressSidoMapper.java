package com.earth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.AddressSidoDTO;

@Mapper
public interface AddressSidoMapper {

	List<AddressSidoDTO> selectAll() throws Exception;
	
	AddressSidoDTO select(String code) throws Exception;
	
}
