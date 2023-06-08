package com.earth.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
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
	
}
