package com.earth.heart.domain;

public class PageResolver {
	
	private 	  SearchItem 	sc;
	
	private 	  int 	  		totalCnt;		 	//게시물 총 개수
	private 	  int 	  		pageSize;		 	//한 페이지 당 게시물 개수
	private final int 	  		NAV_SIZE = 10; 		//페이지 내비게이션 사이즈
	
	private 	  int 	  		totalpage;	 		//전체 페이지 개수
	private 	  int 	  		page;			 	//현재 페이지
	
	private 	  int 	  		beginpage;	 		//내비게이션 첫 페이지 숫자
	private 	  int 	  		endpage;	     	//내비게이션 마지막 페이지 숫자
	private       boolean 		showNext = false;	//다음 페이지로 이동하는 링크를 보여줄지 여부
	private       boolean 		showPrev = false;	//이전 페이지로 이동하는 링크를 보여줄지 여부
	
	public PageResolver(int totalCnt, Integer page) {
		this(totalCnt, new SearchItem(page, 10));
	}
	
	public PageResolver(int totalCnt, Integer page, int pageSize) {
		this(totalCnt, new SearchItem(page, pageSize));
	}
	
	public PageResolver(int totalCnt, SearchItem sc) {
		this.totalCnt = totalCnt;
		this.sc = sc;
		
		doPaging(totalCnt, sc);
	}

	private void doPaging(int totalCnt, SearchItem sc) {
		this.totalpage = (totalCnt % pageSize == 0) ? (totalCnt / pageSize) : (totalCnt / pageSize + 1);
	}
	
}
