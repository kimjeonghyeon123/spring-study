package com.earth.withdang.domain;

public class DanggeunPageResolver {

	private DanggeunSearchItem dsc;
	
	private int totalCnt;
	private Integer pageSize;
	private int NAV_SIZE = 10;
	
	private int totalPage;
	private int page;
	
	private int beginPage;
	private int endPage;
	private boolean showNext = false;
	private boolean showPrev = false;

	public DanggeunPageResolver() {}
	
	public DanggeunPageResolver(int totalCnt, Integer page) {
		this(totalCnt, new DanggeunSearchItem(page, 8));
	}
	
	public DanggeunPageResolver(int totalCnt, Integer page, Integer pageSize) {
		this(totalCnt, new DanggeunSearchItem(page, pageSize));
	}
	
	public DanggeunPageResolver(int totalCnt, DanggeunSearchItem dsc) {
		this.totalCnt = totalCnt;
		this.dsc = dsc;
		
		doPaging(totalCnt, dsc);
	}
	
	private void doPaging(int totalCnt, DanggeunSearchItem dsc) {
		this.totalPage = totalCnt / dsc.getPageSize() + (totalCnt % dsc.getPageSize() == 0 ? 0 : 1);
		this.dsc.setPage(Math.min(dsc.getPage(), totalPage));
		
		this.beginPage = (this.dsc.getPage() - 1) / NAV_SIZE * NAV_SIZE + 1;
		this.endPage = Math.min(this.beginPage + this.NAV_SIZE - 1, totalPage);
		this.showPrev = beginPage != 1;
		this.showNext = endPage != totalPage;
	}

	public DanggeunSearchItem getDsc() {
		return dsc;
	}

	public void setDsc(DanggeunSearchItem dsc) {
		this.dsc = dsc;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getNAV_SIZE() {
		return NAV_SIZE;
	}

	public void setNAV_SIZE(int nAV_SIZE) {
		NAV_SIZE = nAV_SIZE;
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
	
}






















