package com.earth.jeonghyeonkim.domain;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DanggeunTypeDTO other = (DanggeunTypeDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
}
