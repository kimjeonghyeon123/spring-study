package com.earth.jeonghyeonkim.domain;

import java.util.Date;
import java.util.Objects;

public class DangBoardDTO {
	private Integer bno;
	private String title;
	private String wirter;
	private String content;
	private Date reg_date;
	private int view_cnt;
	
	public DangBoardDTO () {
		this("", "", "");
	}

	public DangBoardDTO(String title, String wirter, String content) {
		this.title = title;
		this.wirter = wirter;
		this.content = content;
	}

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWirter() {
		return wirter;
	}

	public void setWirter(String wirter) {
		this.wirter = wirter;
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

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bno, title, wirter);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DangBoardDTO other = (DangBoardDTO) obj;
		return Objects.equals(bno, other.bno) && Objects.equals(title, other.title)
				&& Objects.equals(wirter, other.wirter);
	}	
	
}
