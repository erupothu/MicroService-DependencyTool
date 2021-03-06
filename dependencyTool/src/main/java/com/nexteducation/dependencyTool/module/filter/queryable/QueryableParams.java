package com.nexteducation.dependencyTool.module.filter.queryable;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import io.swagger.annotations.ApiParam;

public class QueryableParams {

	@QueryParam("sort_by")
	@ApiParam(value = "Sort Response By")
	private String sortBy;

	@QueryParam("filter")
	@ApiParam(value = "Filter Response By")
	private String filter;

	@QueryParam("page")
	@ApiParam(value = "Pageable - Page Number")
	private int page;

	@QueryParam("page_size")
	@ApiParam(value = "Pageable - Page Size")
	private int pageSize;

	@QueryParam("select")
	@ApiParam(value = "Jersey Filtering - Get specified values")
	private String select;

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

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
		return "QueryableParams [sortBy=" + sortBy + ", filter=" + filter + ", page=" + page + ", pageSize=" + pageSize
				+ ", select=" + select + "]";
	}

	public Queryable build() {
		Queryable queryable = new Queryable();
		if (sortBy != null && !"".equals(sortBy)) {
			String[] sorts = sortBy.split(",");
			List<Sortable> sortables = new ArrayList<Sortable>();
			for (String sort : sorts) {
				Sortable sortable = new Sortable();
				if (sort.contains("-")) {
					sortable.setOperator(SortableOperator.Descending);
				} else {
					sortable.setOperator(SortableOperator.Ascending);
				}
				sortable.setField(sort.replaceFirst("-", ""));
				sortables.add(sortable);
			}
			queryable.setSortables(sortables);
		}
		Pageable pageable = new Pageable();
		if (page <= 0) {
			pageable.setPage(1);
		} else {
			pageable.setPage(1);
		}
		if (pageSize > 0) {
			pageable.setPageSize(pageSize);
		}
		queryable.setPageable(pageable);

		if (filter != null && !"".equals(filter)) {

		}
		return queryable;
	}
}
