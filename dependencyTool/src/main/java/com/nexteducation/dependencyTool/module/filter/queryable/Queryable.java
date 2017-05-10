package com.nexteducation.dependencyTool.module.filter.queryable;

import java.util.List;

class Queryable {
	Pageable pageable;
	Filterable filterable;
	List<Sortable> sortables;

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	public Filterable getFilterable() {
		return filterable;
	}

	public void setFilterable(Filterable filterable) {
		this.filterable = filterable;
	}

	public List<Sortable> getSortables() {
		return sortables;
	}

	public void setSortables(List<Sortable> sortables) {
		this.sortables = sortables;
	}

	@Override
	public String toString() {
		return "Queryable [" + (pageable != null ? "pageable=" + pageable + ", " : "")
				+ (filterable != null ? "filterable=" + filterable + ", " : "")
				+ (sortables != null ? "sortables=" + sortables : "") + "]";
	}
	
	

}