package com.earth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.DanggeunPhotoDTO;

@Mapper
public interface DanggeunPhotoMapper {

	List<DanggeunPhotoDTO> selectAllByDanggeunId(Integer danggeun_id) throws Exception;
	
	int insert(DanggeunPhotoDTO danggeunPhotoDTO) throws Exception;
	
	int deleteAll(Integer danggeun_id) throws Exception;
	
	int delete(Integer id) throws Exception;
	
}
