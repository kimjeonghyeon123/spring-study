package com.earth.withdang.dao;

import java.util.List;

import com.earth.withdang.domain.DanggeunPhotoDTO;

public interface DanggeunPhotoDAO {

	List<DanggeunPhotoDTO> selectAllByDanggeunId(Integer danggeun_id) throws Exception;
	
	int insert(DanggeunPhotoDTO danggeunPhotoDTO) throws Exception;
	
	int deleteAll(Integer danggeun_id) throws Exception;
	
}
