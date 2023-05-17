package com.earth.jeonghyeonkim.domain;

import static java.util.Objects.requireNonNullElse;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchItem {
	public static final int DEFAULT_PAGE_SIZE = 5;
	public static final int MIN_PAGE_SIZE = 5;
	public static final int MAX_PAGE_SIZE = 50;
	
	private Integer page = 1;
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	private String keyword = "";
	private String option = "";
	private Integer danggeun_type = 0;
	
	public SearchItem() {}

	public SearchItem(Integer page, Integer pageSize) {
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

	public Integer getDanggeun_type() {
		return danggeun_type;
	}

	public void setDanggeun_type(Integer danggeun_type) {
		this.danggeun_type = danggeun_type;
	}
	
	public String getQueryString() {
		return getQueryString(page);
	}
	
	//?page=&pageSize=&keyword=&option=&danggeun_type=
	public String getQueryString(Integer page) {
		return UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("pageSize", pageSize)
				.queryParam("keyword", keyword)
				.queryParam("option", option)
				.queryParam("danggeun_type", danggeun_type)
				.build().toString();
	}
	
	public Integer getOffset() {
		int result = (page - 1)*pageSize;
		if(result < 0) result = 0;
		return result;
	}
	
}
