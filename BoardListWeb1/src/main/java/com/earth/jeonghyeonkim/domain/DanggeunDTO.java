package com.earth.jeonghyeonkim.domain;

import java.util.Date;
import java.util.Objects;


public class DanggeunDTO {
	private Integer id;
	private String title;
	private String name;
	private Integer type_id;
	private Integer local_id;
	private String local_name;
	private int price;
	private String content;
	private String writer;
	private int view_cnt;
	private int add_cnt;
	private Date reg_date;
	private Date up_date;
	
	public DanggeunDTO() {
		this("", "", 0, 0, 0, "", "");
	}
	
	public DanggeunDTO(String title, String name, Integer type_id, Integer local_id, int price, String content,
			String writer) {
		this.title = title;
		this.name = name;
		this.type_id = type_id;
		this.local_id = local_id;
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

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Integer getLocal_id() {
		return local_id;
	}

	public void setLocal_id(Integer local_id) {
		this.local_id = local_id;
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
		return Objects.hash(add_cnt, content, id, local_id, name, price, reg_date, title, type_id, up_date, view_cnt,
				writer);
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
				&& Objects.equals(local_id, other.local_id) && Objects.equals(name, other.name) && price == other.price
				&& Objects.equals(reg_date, other.reg_date) && Objects.equals(title, other.title)
				&& Objects.equals(type_id, other.type_id) && Objects.equals(up_date, other.up_date)
				&& view_cnt == other.view_cnt && Objects.equals(writer, other.writer);
	}

}
