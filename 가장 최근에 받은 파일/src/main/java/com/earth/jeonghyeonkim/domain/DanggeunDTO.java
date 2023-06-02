package com.earth.jeonghyeonkim.domain;

import java.util.Date;
import java.util.Objects;


public class DanggeunDTO {
	private Integer id;
	private String title;
	private String name;
	private Integer type_id;
	private Integer local_id;
	private String local_name;		//추가
	private int price;
	private String content;
	private String writer_email;
	private String writer_name;		//추가
	private int view_cnt;
	private int zzim_cnt;
	private Date reg_date;
	private Date up_date;
	private boolean isStoreByCurrentMember = false; // 추가
	
	public DanggeunDTO() {}
	
	public DanggeunDTO(String title, String name, Integer type_id, Integer local_id, int price, String content, String writer_email) {
		this.title = title;
		this.name = name;
		this.type_id = type_id;
		this.local_id = local_id;
		this.price = price;
		this.content = content;
		this.writer_email = writer_email;
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

	public String getLocal_name() {
		return local_name;
	}

	public void setLocal_name(String local_name) {
		this.local_name = local_name;
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

	@Override
	public int hashCode() {
		return Objects.hash(zzim_cnt, content, id, isStoreByCurrentMember, local_id, local_name, name, price, reg_date,
				title, type_id, up_date, view_cnt, writer_email, writer_name);
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
		return zzim_cnt == other.zzim_cnt && Objects.equals(content, other.content) && Objects.equals(id, other.id)
				&& isStoreByCurrentMember == other.isStoreByCurrentMember && Objects.equals(local_id, other.local_id)
				&& Objects.equals(local_name, other.local_name) && Objects.equals(name, other.name)
				&& price == other.price && Objects.equals(reg_date, other.reg_date)
				&& Objects.equals(title, other.title) && Objects.equals(type_id, other.type_id)
				&& Objects.equals(up_date, other.up_date) && view_cnt == other.view_cnt
				&& Objects.equals(writer_email, other.writer_email) && Objects.equals(writer_name, other.writer_name);
	}

	@Override
	public String toString() {
		return "DanggeunDTO [id=" + id + ", title=" + title + ", name=" + name + ", type_id=" + type_id + ", local_id="
				+ local_id + ", local_name=" + local_name + ", price=" + price + ", content=" + content
				+ ", writer_email=" + writer_email + ", writer_name=" + writer_name + ", view_cnt=" + view_cnt
				+ ", zzim_cnt=" + zzim_cnt + ", reg_date=" + reg_date + ", up_date=" + up_date
				+ ", isStoreByCurrentMember=" + isStoreByCurrentMember + "]";
	}
	
}
