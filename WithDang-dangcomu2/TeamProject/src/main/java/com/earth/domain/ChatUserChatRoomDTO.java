package com.earth.domain;

import lombok.Data;

@Data
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

}
