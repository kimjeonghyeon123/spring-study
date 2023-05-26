package com.earth.korea.domain;

import java.sql.Timestamp;

public class ChatRoomDTO {
	
	private Integer id; 
	private String recent_id;
	private String recent_chat;
	private Timestamp recent_date;
	private int unread_cnt;
	
	private String other_id; //추가
	
	public ChatRoomDTO() {}
	
	public ChatRoomDTO(String recent_id, String recent_chat) {
		this.recent_id = recent_id;
		this.recent_chat = recent_chat;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecent_id() {
		return recent_id;
	}

	public void setRecent_id(String recent_id) {
		this.recent_id = recent_id;
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

	public String getOther_id() {
		return other_id;
	}

	public void setOther_id(String other_id) {
		this.other_id = other_id;
	}
	
}
