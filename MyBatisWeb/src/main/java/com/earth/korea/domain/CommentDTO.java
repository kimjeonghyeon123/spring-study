package com.earth.korea.domain;

import java.util.Date;
import java.util.Objects;

/*
	cno				serial
	,bno 			int 		not null
	,pcon			int 
	,comment		varchar(3000)
	,commenter		varchar(30)
	,reg_date		date	default now()
	,up_date 		date 	default now() * 
 */
public class CommentDTO {
	private Integer cno;
	private Integer bno;
	private Integer pcon;
	private String comment;
	private String commenter;
	private Date reg_date;
	private Date up_date;
	
	public CommentDTO() {}

	public CommentDTO(Integer bno, Integer pcon, String comment, String commenter) {
		super();
		this.bno = bno;
		this.pcon = pcon;
		this.comment = comment;
		this.commenter = commenter;
	}

	public Integer getCno() {
		return cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public Integer getPcon() {
		return pcon;
	}

	public void setPcon(Integer pcon) {
		this.pcon = pcon;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommenter() {
		return commenter;
	}

	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getUp_date() {
		return up_date;
	}

	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}



	@Override
	public String toString() {
		return "CommentDTO [cno=" + cno + ", bno=" + bno + ", pcon=" + pcon + ", comment=" + comment + ", commenter="
				+ commenter + ", reg_date=" + reg_date + ", up_date=" + up_date + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bno, cno, comment, commenter, pcon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentDTO other = (CommentDTO) obj;
		return Objects.equals(bno, other.bno) && Objects.equals(cno, other.cno)
				&& Objects.equals(comment, other.comment) && Objects.equals(commenter, other.commenter)
				&& Objects.equals(pcon, other.pcon);
	}
	
	
}










