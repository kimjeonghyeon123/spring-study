package com.earth.domain;

import java.util.Date;

import lombok.Data;

@Data
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
	private boolean zzimed; // 추가

	public DanggeunInfoDTO() {}

	public void setZzimed(boolean zzimed) {
		this.zzimed = zzimed;
	}
	
	public boolean getZzimed() {
		return zzimed;
	}
	
}
