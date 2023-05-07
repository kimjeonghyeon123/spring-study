package com.earth.jeonghyeonkim.domain;

import java.util.Objects;

//id serial primary key,
//name varchar(50) not null

public class DanggeunTypeDTO {
	private Integer id;
	private String name;
	
	public DanggeunTypeDTO() {
		this("");
	}
	
	public DanggeunTypeDTO(String name) {
		this.name = name;
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
