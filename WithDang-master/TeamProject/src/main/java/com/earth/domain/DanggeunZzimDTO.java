package com.earth.domain;

import lombok.Data;

@Data
public class DanggeunZzimDTO {
	private String user_nickname;
	private Integer danggeun_id;
	
	public DanggeunZzimDTO() {}

	public DanggeunZzimDTO(String user_nickname, Integer danggeun_id) {
		this.user_nickname = user_nickname;
		this.danggeun_id = danggeun_id;
	}

}
