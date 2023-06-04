package com.earth.danggeun.domain;

public class SigoonDTO {

	private String sigoon_code;
	private String sigoon_name;
	
	public SigoonDTO() {}

	public SigoonDTO(String sigoon_code, String sigoon_name) {
		this.sigoon_code = sigoon_code;
		this.sigoon_name = sigoon_name;
	}

	public String getSigoon_code() {
		return sigoon_code;
	}

	public void setSigoon_code(String sigoon_code) {
		this.sigoon_code = sigoon_code;
	}

	public String getSigoon_name() {
		return sigoon_name;
	}

	public void setSigoon_name(String sigoon_name) {
		this.sigoon_name = sigoon_name;
	}
	
}
