package com.earth.jeonghyeonkim.domain;

import java.util.Date;
import java.util.Objects;

//id 			serial 		primary key,-- 상품아이디
//title		varchar(50) not null,
//name 		varchar(50) not null,	-- 상품이름
//price		int			not null,	-- 상품가격
//content 	text 		not null,	-- 상품설명
//writer 		varchar(50) not null,	-- 판매자
//view_cnt 	int 		default 0,	-- 조회수
//add_cnt		int			default 0,	-- 찜 수
//reg_date 	date 		default current_timestamp,
//up_date 	date 		default current_timestamp

public class DanggeunDTO {
	private Integer id;
	private String title;
	private String name;
	private Integer type_id;
	private int price;
	private String content;
	private String writer;
	private int view_cnt;
	private int add_cnt;
	private Date reg_date;
	private Date up_date;
	
	public DanggeunDTO() {
		this("", "", 0, "", "");
	}

	public DanggeunDTO(String title, String name, int price, String content, String writer) {
		this.title = title;
		this.name = name;
		this.price = price;
		this.content = content;
		this.writer = writer;
	}

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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public int getAdd_cnt() {
		return add_cnt;
	}

	public void setAdd_cnt(int add_cnt) {
		this.add_cnt = add_cnt;
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
	public int hashCode() {
		return Objects.hash(add_cnt, content, id, name, price, reg_date, title, up_date, view_cnt, writer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DanggeunDTO other = (DanggeunDTO) obj;
		return add_cnt == other.add_cnt && Objects.equals(content, other.content) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && price == other.price && Objects.equals(reg_date, other.reg_date)
				&& Objects.equals(title, other.title) && Objects.equals(up_date, other.up_date)
				&& view_cnt == other.view_cnt && Objects.equals(writer, other.writer);
	}

}
