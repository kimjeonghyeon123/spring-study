package com.earth.domain;

import lombok.Data;

@Data
public class DanggeunPhotoDTO {

	private Integer id;
	private String address;
	private Integer danggeun_id;
	
	public DanggeunPhotoDTO() {}

	public DanggeunPhotoDTO(String address, Integer danggeun_id) {
		this.address = address;
		this.danggeun_id = danggeun_id;
	}
	
}
