package com.earth.domain;

import java.sql.Timestamp;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSender_nickname() {
		return sender_nickname;
	}

	public void setSender_nickname(String sender_nickname) {
		this.sender_nickname = sender_nickname;
	}

	public Integer getChatroom_id() {
		return chatroom_id;
	}

	public void setChatroom_id(Integer chatroom_id) {
		this.chatroom_id = chatroom_id;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	public Timestamp getChat_date() {
		return chat_date;
	}

	public void setChat_date(Timestamp chat_date) {
		this.chat_date = chat_date;
	}

	public boolean getCheck_read() {
		return check_read;
	}

	public void setCheck_read(boolean check_read) {
		this.check_read = check_read;
	}
	
}
