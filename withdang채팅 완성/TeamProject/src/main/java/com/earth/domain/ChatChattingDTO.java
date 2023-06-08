package com.earth.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChatChattingDTO {
	
	private Integer id;
	private String sender_nickname;
	private Integer chatroom_id;
	private String chat;
	private Timestamp chat_date;
	private boolean check_read;
	
	public ChatChattingDTO() {}
	
	public ChatChattingDTO(String sender_nickname, Integer chatroom_id, String chat, Timestamp chat_date) {
		this.sender_nickname = sender_nickname;
		this.chatroom_id = chatroom_id;
		this.chat = chat;
		this.chat_date = chat_date;
	}
	
}
