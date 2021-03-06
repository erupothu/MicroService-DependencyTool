package com.nexteducation.dependencyTool.module.filter.queryable;

class Filterable {
	Filterable first;
	FilterableOperator operator;
	Filterable second;
	String field;
	String value;

	public Filterable getFirst() {
		return first;
	}

	public void setFirst(Filterable first) {
		this.first = first;
	}

	public FilterableOperator getOperator() {
		return operator;
	}

	public void setOperator(FilterableOperator operator) {
		this.operator = operator;
	}

	public Filterable getSecond() {
		return second;
	}

	public void setSecond(Filterable second) {
		this.second = second;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Filterable [" + (first != null ? "first=" + first + ", " : "")
				+ (operator != null ? "operator=" + operator + ", " : "")
				+ (second != null ? "second=" + second + ", " : "") + (field != null ? "field=" + field + ", " : "")
				+ (value != null ? "value=" + value : "") + "]";
	}	
}