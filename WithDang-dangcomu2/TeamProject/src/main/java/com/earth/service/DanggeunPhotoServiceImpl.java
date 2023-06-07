package com.earth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.earth.domain.DanggeunPhotoDTO;
import com.earth.mapper.DanggeunPhotoMapper;
import com.earth.upload.S3UploadService;

@Service
public class DanggeunPhotoServiceImpl implements DanggeunPhotoService {

	private DanggeunPhotoMapper danggeunPhotoMapper;
	private S3UploadService s3UploadService;
	
	public DanggeunPhotoServiceImpl(DanggeunPhotoMapper danggeunPhotoMapper, S3UploadService s3UploadService) {
		this.danggeunPhotoMapper = danggeunPhotoMapper;
		this.s3UploadService = s3UploadService;
	}

	@Override
	public DanggeunPhotoDTO registerPhoto(String address, Integer danggeun_id) throws Exception {
		DanggeunPhotoDTO danggeunPhotoDTO = new DanggeunPhotoDTO(address, danggeun_id);
		danggeunPhotoMapper.insert(danggeunPhotoDTO);
		return danggeunPhotoDTO;
	}

	@Override
	public List<DanggeunPhotoDTO> showPhoto(Integer danggeun_id) throws Exception {
		return danggeunPhotoMapper.selectAllByDanggeunId(danggeun_id);
	}

}
