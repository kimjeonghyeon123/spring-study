package com.earth.korea.domain;

import java.util.Date;
import java.util.Objects;

public class ChatLogDTO {
	private Integer id;
	private Integer chatroom_id;
	private String user_id;
	private String content;
	private Date chat_date;
	private boolean check_status;
	
	public ChatLogDTO() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChatroom_id() {
		return chatroom_id;
	}

	public void setChatroom_id(Integer chatroom_id) {
		this.chatroom_id = chatroom_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getChat_date() {
		return chat_date;
	}

	public void setChat_date(Date chat_date) {
		this.chat_date = chat_date;
	}

	public boolean isCheck_status() {
		return check_status;
	}

	public void setCheck_status(boolean check_status) {
		this.check_status = check_status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chat_date, chatroom_id, check_status, content, id, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatLogDTO other = (ChatLogDTO) obj;
		return Objects.equals(chat_date, other.chat_date) && Objects.equals(chatroom_id, other.chatroom_id)
				&& check_status == other.check_status && Objects.equals(content, other.content)
				&& Objects.equals(id, other.id) && Objects.equals(user_id, other.user_id);
	}
	
}
