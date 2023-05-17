package com.earth.heart;

public class CustomerUser {
	
	private int userid;
	private String username;
	
	public CustomerUser() {}
	
	public CustomerUser(int userid, String username) {
		this.userid = userid;
		this.username = username;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
