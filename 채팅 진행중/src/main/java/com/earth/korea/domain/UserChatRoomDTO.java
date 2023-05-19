package com.earth.korea.domain;

import java.util.Objects;

public class UserChatRoomDTO {
	private String user_id;
	private Integer chatroom_id;
	
	public UserChatRoomDTO() {}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getChatroom_id() {
		return chatroom_id;
	}

	public void setChatroom_id(Integer chatroom_id) {
		this.chatroom_id = chatroom_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chatroom_id, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserChatRoomDTO other = (UserChatRoomDTO) obj;
		return Objects.equals(chatroom_id, other.chatroom_id) && Objects.equals(user_id, other.user_id);
	}
	
}
