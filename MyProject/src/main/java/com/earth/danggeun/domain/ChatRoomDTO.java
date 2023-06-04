package com.earth.danggeun.domain;

import java.sql.Timestamp;

public class ChatRoomDTO {
	
	private Integer id; 
	private String recent_email;
	private String recent_chat;
	private Timestamp recent_date;
	private int unread_cnt;
	
	private String other_email; //추가
	
	public ChatRoomDTO() {}
	
	public ChatRoomDTO(String recent_email, String recent_chat) {
		this.recent_email = recent_email;
		this.recent_chat = recent_chat;
	}
	
	public ChatRoomDTO(Integer id, String recent_email, String recent_chat) {
		this.id = id;
		this.recent_email = recent_email;
		this.recent_chat = recent_chat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecent_email() {
		return recent_email;
	}

	public void setRecent_email(String recent_email) {
		this.recent_email = recent_email;
	}

	public String getRecent_chat() {
		return recent_chat;
	}

	public void setRecent_chat(String recent_chat) {
		this.recent_chat = recent_chat;
	}

	public Timestamp getRecent_date() {
		return recent_date;
	}

	public void setRecent_date(Timestamp recent_date) {
		this.recent_date = recent_date;
	}

	public int getUnread_cnt() {
		return unread_cnt;
	}

	public void setUnread_cnt(int unread_cnt) {
		this.unread_cnt = unread_cnt;
	}

	public String getOther_email() {
		return other_email;
	}

	public void setOther_id(String other_email) {
		this.other_email = other_email;
	}
	
}
