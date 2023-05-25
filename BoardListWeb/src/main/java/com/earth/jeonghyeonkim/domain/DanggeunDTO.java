package com.earth.jeonghyeonkim.domain;

import java.util.Date;
import java.util.Objects;


public class DanggeunDTO {
	private Integer id;
	private String title;
	private String name;
	private Integer type_id;
	private String type_name;
	private int price;
	private String content;
	private String writer_email;
	private String writer_name;
	private int view_cnt;
	private int zzim_cnt;
	private Date reg_date;
	private Date up_date;
	private boolean isStoreByCurrentMember = false; // 추가
	
	public DanggeunDTO() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter_email() {
		return writer_email;
	}

	public void setWriter_email(String writer_email) {
		this.writer_email = writer_email;
	}

	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public int getZzim_cnt() {
		return zzim_cnt;
	}

	public void setZzim_cnt(int zzim_cnt) {
		this.zzim_cnt = zzim_cnt;
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

	public boolean getIsStoreByCurrentMember() {
		return isStoreByCurrentMember;
	}

	public void setIsStoreByCurrentMember(boolean isStoreByCurrentMember) {
		this.isStoreByCurrentMember = isStoreByCurrentMember;
	}
	
	
	
}
