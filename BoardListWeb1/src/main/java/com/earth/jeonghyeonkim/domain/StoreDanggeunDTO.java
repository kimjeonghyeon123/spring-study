package com.earth.jeonghyeonkim.domain;

public class StoreDanggeunDTO {
	private String member_email;
	private Integer danggeun_id;
	
	public StoreDanggeunDTO() {}

	public StoreDanggeunDTO(String member_email, Integer danggeun_id) {
		this.member_email = member_email;
		this.danggeun_id = danggeun_id;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public Integer getDanggeun_id() {
		return danggeun_id;
	}

	public void setDanggeun_id(Integer danggeun_id) {
		this.danggeun_id = danggeun_id;
	}
	
}
