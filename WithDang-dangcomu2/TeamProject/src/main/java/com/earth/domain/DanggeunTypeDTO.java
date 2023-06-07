package com.earth.domain;

import lombok.Data;

@Data
public class DanggeunTypeDTO {
	private Integer id 		= 0;
	private String 	name 	= "전체";
	
	public DanggeunTypeDTO() {}
	
	public DanggeunTypeDTO(Integer id, String name) {
		this.id 	= id;
		this.name 	= name;
	}
	
}
