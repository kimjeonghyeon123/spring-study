package com.earth.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class MemberDto {
	
	private String user_email;
	private String user_nickname;
	private String user_pw;
	private String user_name;
	private String user_pnum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date user_birth;
	private String user_gender;
	private String user_address;
	private String user_dtl_address;
	
	@Override
	public String toString() {
		return "MemberDto [user_email=" + user_email + ", user_nickname=" + user_nickname + ", user_pw=" + user_pw
				+ ", user_name=" + user_name + ", user_pnum=" + user_pnum + ", user_birth=" + user_birth
				+ ", user_gender=" + user_gender + ", user_address=" + user_address + ", user_dtl_address="
				+ user_dtl_address + "]";
	}
	
}
