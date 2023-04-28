package com.earth.zproject3.domain;

import java.util.Objects;

public class DangMember {
	private String email;
	private String pwd;
	private String name;
	
	public DangMember() {}

	public DangMember(String email, String pwd, String name) {
		this.email = email;
		this.pwd = pwd;
		this.name = name;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(email, name, pwd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DangMember other = (DangMember) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name) && Objects.equals(pwd, other.pwd);
	}
}
