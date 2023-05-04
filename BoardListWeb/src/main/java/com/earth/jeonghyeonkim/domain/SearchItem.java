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

	//SearchItem 객체를 만드려면 현재 페이지, 페이지 사이즈, 등의 정보가 필요하다
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
		
		// 입력받은 페이지 사이즈와 50 중에 작은 거, 5중에 큰 거
		// 5 <= pageSize <= 50 로 만들어줌
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

	// get을 써서 mapper에서 offset을 사용할 수 있게 해줌
	public Integer getOffset() {
		int result = (page - 1) * pageSize;
		if(result < 0) result = 0;
		return result;
	}
	
	public String getQueryString() {
		return getQueryString(page);
	}

	// 밑에 GET 방식을 만들어줌
	// ?page=10&pageSize=10&option=A&keyword=title
	public String getQueryString(Integer page) {
		return UriComponentsBuilder.newInstance()
			   .queryParam("page", page)
			   .queryParam("pageSize", pageSize)
			   .queryParam("option", option)
			   .queryParam("keyword", keyword).build().toString();
	}
}
