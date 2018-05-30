package com.ll.medical.model.base;

public class BaseQuery {

	private Integer pageNumber;
	private Integer pageSize;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "BaseQuery [pageNumber=" + pageNumber + ", pageSize=" + pageSize + "]";
	}

}
