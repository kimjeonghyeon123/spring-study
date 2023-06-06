package com.earth.withdang.domain;

import java.util.Date;

public class DanggeunMemberDTO {
	
	private String user_email;
	private String user_nickname;
	private String user_pw;
	private String user_name;
	private String user_pnum;
	private Date user_birth;
	private String user_gender;
	private String user_address;
	private String user_dtl_address;
	
	public DanggeunMemberDTO() {}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pnum() {
		return user_pnum;
	}

	public void setUser_pnum(String user_pnum) {
		this.user_pnum = user_pnum;
	}

	public Date getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(Date user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_dtl_address() {
		return user_dtl_address;
	}

	public void setUser_dtl_address(String user_dtl_address) {
		this.user_dtl_address = user_dtl_address;
	}
	
}
