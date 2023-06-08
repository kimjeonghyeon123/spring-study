package com.earth.domain;

import java.sql.Timestamp;

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

	public boolean getParticipation() {
		return participation;
	}

	public void setParticipation(boolean participation) {
		this.participation = participation;
	}

	public Timestamp getParticipation_date() {
		return participation_date;
	}

	public void setParticipation_date(Timestamp participation_date) {
		this.participation_date = participation_date;
	}

}
