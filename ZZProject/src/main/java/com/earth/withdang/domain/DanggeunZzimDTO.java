package com.earth.withdang.domain;

public class DanggeunZzimDTO {
	private String user_nickname;
	private Integer danggeun_id;
	
	public DanggeunZzimDTO() {}

	public DanggeunZzimDTO(String user_nickname, Integer danggeun_id) {
		this.user_nickname = user_nickname;
		this.danggeun_id = danggeun_id;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public Integer getDanggeun_id() {
		return danggeun_id;
	}

	public void setDanggeun_id(Integer danggeun_id) {
		this.danggeun_id = danggeun_id;
	}

}
