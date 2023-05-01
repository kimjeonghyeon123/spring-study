package com.earth.heart.domain;

import static java.util.Objects.requireNonNullElse;

public class SearchItem {
	
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int MIN_PAGE_SIZE = 5;
	public static final int MAX_PAGE_SIZE = 50;
	
	private Integer page 		= 1;
	private Integer pageSize 	= DEFAULT_PAGE_SIZE;
	private String 	keyword 	= "";
	private String 	option 		= "";
	
	public SearchItem() {}

	public SearchItem(Integer page, Integer pageSize) {
		this(page, pageSize, "", "");
	}

	public SearchItem(Integer page, Integer pageSize, String keyword, String option) {
		this.page 		= page;
		this.pageSize 	= pageSize;
		this.keyword 	= keyword;
		this.option 	= option;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = requireNonNullElse(pageSize, DEFAULT_PAGE_SIZE);
		
		this.pageSize = Math.max(MIN_PAGE_SIZE, Math.min(this.pageSize, MAX_PAGE_SIZE));
	}
	
}
