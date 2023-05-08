package com.earth.jeonghyeonkim.domain;

public class PageResolver {

	private SearchItem sc;
	
	private int totalCnt;
	private int pageSize;
	private final int NAV_SIZE = 10;
	
	private int totalPage;
	private int page;
	
	private int beginPage;
	private int endPage;
	private boolean showNext = false;
	private boolean showPrev = false;
	
	public PageResolver(int totalCnt, Integer page) {
		this(totalCnt, new SearchItem(page, 10));
	}
	
	public PageResolver(int totalCnt, Integer page, int pageSize) {
		this(totalCnt, new SearchItem(page, pageSize));
	}

	//PageResolver 객체를 만드려면 총 게시물 개수와 SearchItem 객체가 필요하다
	public PageResolver(int totalCnt, SearchItem sc) {
		this.totalCnt = totalCnt;
		this.sc = sc;
		
		doPaging(totalCnt, sc);
	}

	//페이지 구성 설정
	private void doPaging(int totalCnt2, SearchItem sc) {
		//전체 페이지 개수 구하기
		this.totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize() == 0 ? 0 : 1);
		//SearchItem 객체에 현재 페이지를 설정
		this.sc.setPage(Math.min(sc.getPage(), totalPage));
		
		this.beginPage = (sc.getPage() - 1) / NAV_SIZE * NAV_SIZE + 1;
		
		this.endPage = Math.min(beginPage + NAV_SIZE - 1, totalPage);
		
		this.showPrev = this.beginPage != 1;
		this.showNext = this.endPage != totalPage;
	}

	public SearchItem getSc() {
		return sc;
	}

	public void setSc(SearchItem sc) {
		this.sc = sc;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isShowNext() {
		return showNext;
	}

	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}

	public boolean isShowPrev() {
		return showPrev;
	}

	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}

	public int getNAV_SIZE() {
		return NAV_SIZE;
	}
}
