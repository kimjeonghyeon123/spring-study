package com.earth.domain;

import java.sql.Timestamp;

public class ChatChatRoomDTO {

	private Integer id; 
	private String recent_nickname;
	private String recent_chat;
	private Timestamp recent_date;
	private int unread_cnt;
	private String other_nickname; //추가

	public ChatChatRoomDTO() {}
	
	public ChatChatRoomDTO(String recent_nickname, String recent_chat, Timestamp recent_date) {
		this.recent_nickname = recent_nickname;
		this.recent_chat = recent_chat;
		this.recent_date = recent_date;
	}

	public ChatChatRoomDTO(Integer id, String recent_nickname, String recent_chat, Timestamp recent_date) {
		this.id = id;
		this.recent_nickname = recent_nickname;
		this.recent_chat = recent_chat;
		this.recent_date = recent_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecent_nickname() {
		return recent_nickname;
	}

	public void setRecent_nickname(String recent_nickname) {
		this.recent_nickname = recent_nickname;
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

	public String getOther_nickname() {
		return other_nickname;
	}

	public void setOther_nickname(String other_nickname) {
		this.other_nickname = other_nickname;
	}
	
}
