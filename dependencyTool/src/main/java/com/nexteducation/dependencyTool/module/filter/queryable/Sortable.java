package com.nexteducation.dependencyTool.module.filter.queryable;

class Sortable {
	String Field;
	SortableOperator operator;

	public String getField() {
		return Field;
	}

	public void setField(String field) {
		Field = field;
	}

	public SortableOperator getOperator() {
		return operator;
	}

	public void setOperator(SortableOperator operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "Sortable [" + (Field != null ? "Field=" + Field + ", " : "")
				+ (operator != null ? "operator=" + operator : "") + "]";
	}

}