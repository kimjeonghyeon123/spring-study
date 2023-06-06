package com.earth.withdang.domain;

import java.util.Date;

public class DanggeunInfoDTO {

	private Integer id;
	private String photo_address;
	private String title;
	private Integer type_id;
	private String type_name;
	private String sido_code;
	private String sido_name;
	private String sigoon_code;
	private String sigoon_name;
	private String dong_code;
	private String dong_name;
	private int price;
	private String content;
	private String writer_nickname;
	private int view_cnt;
	private int zzim_cnt;
	private Date reg_date;
	private Date up_date;
	
	private boolean is_zzimed = false; // 추가

	public DanggeunInfoDTO() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoto_address() {
		return photo_address;
	}

	public void setPhoto_address(String photo_address) {
		this.photo_address = photo_address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getSido_code() {
		return sido_code;
	}

	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}

	public String getSido_name() {
		return sido_name;
	}

	public void setSido_name(String sido_name) {
		this.sido_name = sido_name;
	}

	public String getSigoon_code() {
		return sigoon_code;
	}

	public void setSigoon_code(String sigoon_code) {
		this.sigoon_code = sigoon_code;
	}

	public String getSigoon_name() {
		return sigoon_name;
	}

	public void setSigoon_name(String sigoon_name) {
		this.sigoon_name = sigoon_name;
	}

	public String getDong_code() {
		return dong_code;
	}

	public void setDong_code(String dong_code) {
		this.dong_code = dong_code;
	}

	public String getDong_name() {
		return dong_name;
	}

	public void setDong_name(String dong_name) {
		this.dong_name = dong_name;
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

	public String getWriter_nickname() {
		return writer_nickname;
	}

	public void setWriter_nickname(String writer_nickname) {
		this.writer_nickname = writer_nickname;
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

	public boolean isIs_zzimed() {
		return is_zzimed;
	}

	public void setIs_zzimed(boolean is_zzimed) {
		this.is_zzimed = is_zzimed;
	}

}
