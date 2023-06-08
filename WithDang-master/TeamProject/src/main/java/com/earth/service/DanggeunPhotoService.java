package com.earth.service;

import java.util.List;

import com.earth.domain.DanggeunPhotoDTO;

public interface DanggeunPhotoService {

	DanggeunPhotoDTO registerPhoto(String address, Integer danggeun_id) throws Exception;
	
	List<DanggeunPhotoDTO> showPhoto(Integer danggeun_id) throws Exception;
	
}
