package com.earth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.DanggeunTypeDTO;

@Mapper
public interface DanggeunTypeMapper {

	//타입 목록을 부를 때 사용
	List<DanggeunTypeDTO> selectAll() throws Exception;
	
	//타입 하나 가져올 때 사용
	DanggeunTypeDTO select(Integer id) throws Exception;
	
}
