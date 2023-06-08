package com.earth.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChatUserChatRoomDTO {

	private Integer chatroom_id;
	private String login_nickname;
	private String other_nickname;
	private boolean participation;
	private Timestamp participation_date;
	
	public ChatUserChatRoomDTO() {}

	public ChatUserChatRoomDTO(Integer chatroom_id, String login_nickname, String other_nickname, boolean participation, Timestamp participation_date) {
		this.chatroom_id = chatroom_id;
		this.login_nickname = login_nickname;
		this.other_nickname = other_nickname;
		this.participation = participation;
		this.participation_date = participation_date;
	}

	public void setParticipant(boolean participation) {
		this.participation = participation;
	}
	
	public boolean getParticipation() {
		return participation;
	}

}
