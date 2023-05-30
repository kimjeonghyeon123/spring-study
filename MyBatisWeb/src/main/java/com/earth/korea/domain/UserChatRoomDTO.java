package com.earth.korea.domain;

public class UserChatRoomDTO {
	private Integer chatroom_id;
	private String login_id;
	private String other_id;
	
	public UserChatRoomDTO() {}

	public UserChatRoomDTO(Integer chatroom_id, String login_id, String other_id) {
		this.chatroom_id = chatroom_id;
		this.login_id = login_id;
		this.other_id = other_id;
	}

	public Integer getChatroom_id() {
		return chatroom_id;
	}

	public void setChatroom_id(Integer chatroom_id) {
		this.chatroom_id = chatroom_id;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getOther_id() {
		return other_id;
	}

	public void setOther_id(String other_id) {
		this.other_id = other_id;
	}
	
}
