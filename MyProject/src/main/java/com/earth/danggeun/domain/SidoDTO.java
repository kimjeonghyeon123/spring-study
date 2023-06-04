package com.earth.danggeun.domain;

//sido_code varchar(10) primary key,
//sido_name varchar(15) not null

public class SidoDTO {
	
	private String sido_code;
	private String sido_name;
	
	public SidoDTO() {}

	public SidoDTO(String sido_code, String sido_name) {
		this.sido_code = sido_code;
		this.sido_name = sido_name;
	}
	
	public String getSido_code() {
		return sido_code;
	}

	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}

	public String getSido_name() {
		return sido_name;
	}

	public void setSido_name(String sido_name) {
		this.sido_name = sido_name;
	}
	
}
