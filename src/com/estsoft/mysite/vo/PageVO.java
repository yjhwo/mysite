package com.estsoft.mysite.vo;

public class PageVO {
	private int currentPage;
	private int firstPage;
	private int lastPage;
	private int totalPage;
	private int totalCount;
	
	public PageVO(){	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "PageVO [currentPage=" + currentPage + ", firstPage=" + firstPage + ", lastPage=" + lastPage
				+ ", totalPage=" + totalPage + ", totalCount=" + totalCount + "]";
	}

	
}
