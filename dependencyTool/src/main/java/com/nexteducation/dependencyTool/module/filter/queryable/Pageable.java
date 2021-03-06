package com.nexteducation.dependencyTool.module.filter.queryable;

class Pageable {
	private int page;
	private int pageSize;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "Pageable [page=" + page + ", pageSize=" + pageSize + "]";
	}
	
}