package com.earth.jeonghyeonkim.domain;

import java.util.Objects;

public class ZzimDanggeunDTO {
	private String member_email;
	private Integer danggeun_id;
	
	public ZzimDanggeunDTO() {}

	public ZzimDanggeunDTO(String member_email, Integer danggeun_id) {
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

	@Override
	public int hashCode() {
		return Objects.hash(danggeun_id, member_email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZzimDanggeunDTO other = (ZzimDanggeunDTO) obj;
		return Objects.equals(danggeun_id, other.danggeun_id) && Objects.equals(member_email, other.member_email);
	}
	
}
