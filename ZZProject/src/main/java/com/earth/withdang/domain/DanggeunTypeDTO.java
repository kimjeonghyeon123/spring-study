package com.earth.withdang.domain;

public class DanggeunTypeDTO {
	private Integer id 		= 0;
	private String 	name 	= "전체";
	
	public DanggeunTypeDTO() {}
	
	public DanggeunTypeDTO(Integer id, String name) {
		this.id 	= id;
		this.name 	= name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
