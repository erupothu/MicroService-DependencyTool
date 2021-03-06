package com.nexteducation.dependencyTool.module.filter.queryable;

enum SortableOperator {
	Descending(-1), Ascending(1);
	int value;

	SortableOperator(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}