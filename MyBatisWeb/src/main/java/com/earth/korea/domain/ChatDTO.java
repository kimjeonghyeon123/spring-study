package com.earth.korea.domain;

import java.util.Date;
import java.util.Objects;

public class ChatDTO {
	private Integer id;
	private String sender_id;
	private String content;
	private Date reg_date;
	
	public ChatDTO() {}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, id, reg_date, sender_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatDTO other = (ChatDTO) obj;
		return Objects.equals(content, other.content) && Objects.equals(id, other.id)
				&& Objects.equals(reg_date, other.reg_date) && Objects.equals(sender_id, other.sender_id);
	}
	
	
}
