package com.earth.jeonghyeonkim.domain;

import static java.util.Objects.requireNonNullElse;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchItem {

	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int MIN_PAGE_SIZE = 5;
	public static final int MAX_PAGE_SIZE = 50;
	
	private Integer page 		= 1;//현재 페이지
	private Integer pageSize 	= DEFAULT_PAGE_SIZE;//한 페이지 안에 나올 수 있는 게시물 수
	private String 	keyword 	= "";//검색어
	private String 	option 		= "";//검색 옵션
	
	public SearchItem() {}
	
	public SearchItem(Integer page, Integer pageSize) {
		this(page, pageSize, "", "");
	}
	
	public SearchItem(Integer page, Integer pageSize, String keyword, String option) {
		this.page = page;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.option = option;
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
		this.pageSize = requireNonNullElse(pageSize, DEFAULT_PAGE_SIZE);
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
	
	public Integer getOffset() {
		int result = (page - 1) * pageSize;
		if(result < 0) result = 0;
		return result;
	}
	
	public String getQueryString() {
		return getQueryString(page);
	}

	// GET 방식 쿼리문을 만들어줌
	// ?page=10&pageSize=10&option=A&keyword=title
	public String getQueryString(Integer page) {
		return UriComponentsBuilder.newInstance()
			   .queryParam("page", page)
			   .queryParam("pageSize", pageSize)
			   .queryParam("option", option)
			   .queryParam("keyword", keyword).build().toString();
	}
}
