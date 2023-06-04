package com.earth.danggeun.domain;

public class UserChatRoomDTO {
	private Integer chatroom_id;
	private String login_email;
	private String other_email;
	
	public UserChatRoomDTO() {}

	public UserChatRoomDTO(Integer chatroom_id, String login_email, String other_email) {
		this.chatroom_id = chatroom_id;
		this.login_email = login_email;
		this.other_email = other_email;
	}

	public Integer getChatroom_id() {
		return chatroom_id;
	}

	public void setChatroom_id(Integer chatroom_id) {
		this.chatroom_id = chatroom_id;
	}

	public String getLogin_email() {
		return login_email;
	}

	public void setLogin_email(String login_email) {
		this.login_email = login_email;
	}

	public String getOther_email() {
		return other_email;
	}

	public void setOther_email(String other_email) {
		this.other_email = other_email;
	}
	
}
