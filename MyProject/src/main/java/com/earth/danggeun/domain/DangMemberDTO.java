package com.earth.danggeun.domain;

//email varchar(50) primary key,
//pwd varchar(50) not null,
//name varchar(50) not null

public class DangMemberDTO {

	private String email;
	private String pwd;
	private String name;
	
	public DangMemberDTO(){}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
