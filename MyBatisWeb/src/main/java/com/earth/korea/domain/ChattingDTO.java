package com.earth.korea.domain;

import java.sql.Timestamp;

public class ChattingDTO {
	private Integer id;
	private String sender_id;
	private Integer chatroom_id;
	private String chat;
	private Timestamp chat_date;
	private boolean check_read;
	
	public ChattingDTO() {}
	
	public ChattingDTO(String sender_id, Integer chatroom_id, String chat) {
		this.sender_id = sender_id;
		this.chatroom_id = chatroom_id;
		this.chat = chat;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
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
	public boolean isCheck_read() {
		return check_read;
	}
	public void setCheck_read(boolean check_read) {
		this.check_read = check_read;
	}
	
}
