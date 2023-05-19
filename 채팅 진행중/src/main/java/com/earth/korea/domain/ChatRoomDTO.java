package com.earth.korea.domain;

import java.util.Date;
import java.util.Objects;

public class ChatRoomDTO {

	private Integer id;
	private Date last_date;
	private String last_message;
	
	public ChatRoomDTO() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getLast_date() {
		return last_date;
	}

	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}

	public String getLast_message() {
		return last_message;
	}

	public void setLast_message(String last_message) {
		this.last_message = last_message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, last_date, last_message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatRoomDTO other = (ChatRoomDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(last_date, other.last_date)
				&& Objects.equals(last_message, other.last_message);
	}
	
	
	
}
