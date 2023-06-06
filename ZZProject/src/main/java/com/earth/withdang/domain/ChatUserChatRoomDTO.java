package com.earth.withdang.domain;

public class ChatUserChatRoomDTO {

	private Integer chatroom_id;
	private String login_nickname;
	private String other_nickname;
	
	public ChatUserChatRoomDTO() {}

	public ChatUserChatRoomDTO(Integer chatroom_id, String login_nickname, String other_nickname) {
		this.chatroom_id = chatroom_id;
		this.login_nickname = login_nickname;
		this.other_nickname = other_nickname;
	}

	public Integer getChatroom_id() {
		return chatroom_id;
	}

	public void setChatroom_id(Integer chatroom_id) {
		this.chatroom_id = chatroom_id;
	}

	public String getLogin_nickname() {
		return login_nickname;
	}

	public void setLogin_nickname(String login_nickname) {
		this.login_nickname = login_nickname;
	}

	public String getOther_nickname() {
		return other_nickname;
	}

	public void setOther_nickname(String other_nickname) {
		this.other_nickname = other_nickname;
	}
	
}
