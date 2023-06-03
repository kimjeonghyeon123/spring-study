package com.earth.danggeun.domain;

import static java.util.Objects.requireNonNullElse;

import org.springframework.web.util.UriComponentsBuilder;

public class DanggeunSearchItem {
	
	public static final int DEFAULT_PAGE_SIZE = 8;
	public static final int MIN_PAGE_SIZE = 8;
	public static final int MAX_PAGE_SIZE = 20;
	
	private Integer page = 1;
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	private String keyword = "";
	private String option = "A";
	private Integer type_id = 0;
	private String sido_code = "0";
	private String sigoon_code = "0";
	private String dong_code = "0";
	private String login_email;
	
	public DanggeunSearchItem() {}
	
	public DanggeunSearchItem(Integer page, Integer pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		//pageSize가 null 일 때 default 값을 반환
		this.pageSize = requireNonNullElse(pageSize, DEFAULT_PAGE_SIZE);
		
		//MIN_PAGE_SIZE <= pageSize <= MAX_PAGE_SIZE
		this.pageSize = Math.max(MIN_PAGE_SIZE, Math.min(this.pageSize, MAX_PAGE_SIZE));
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public String getSido_code() {
		return sido_code;
	}

	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}

	public String getSigoon_code() {
		return sigoon_code;
	}

	public void setSigoon_code(String sigoon_code) {
		this.sigoon_code = sigoon_code;
	}
	
	public String getDong_code() {
		return dong_code;
	}

	public void setDong_code(String dong_code) {
		this.dong_code = dong_code;
	}

	public String getQueryString() {
		return getQueryString(page);
	}
	
	public String getQueryString(Integer page) {
		return UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("pageSize", pageSize)
				.queryParam("keyword", keyword)
				.queryParam("option", option)
				.queryParam("type_id", type_id)
				.queryParam("sido_code", sido_code)
				.queryParam("sigoon_code", sigoon_code)
				.queryParam("dong_code", dong_code)
				.build().toString();
	}
	
	public Integer getOffset() {
		int result = (page - 1) * pageSize;
		if(result < 0) result = 0;
		return result;
	}

	public String getLogin_email() {
		return login_email;
	}

	public void setLogin_email(String login_email) {
		this.login_email = login_email;
	}

	@Override
	public String toString() {
		return "DanggeunSearchItem [page=" + page + ", pageSize=" + pageSize + ", keyword=" + keyword + ", option="
				+ option + ", type_id=" + type_id + ", sido_code=" + sido_code + ", sigoon_code=" + sigoon_code
				+ ", dong_code=" + dong_code + ", login_email=" + login_email + "]";
	}

}
















