package com.nexteducation.dependencyTool.util;

public interface Parser {

	@SuppressWarnings("rawtypes")
	public abstract <E> E convertToObject(final String parserString, final Class clasz);

	public abstract String convertToType(final Object object);
}
