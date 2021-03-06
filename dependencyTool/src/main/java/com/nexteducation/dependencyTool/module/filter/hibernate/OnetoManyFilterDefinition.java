package com.nexteducation.dependencyTool.module.filter.hibernate;

import java.util.Map;

import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.type.Type;

@SuppressWarnings("serial")
public class OnetoManyFilterDefinition extends FilterDefinition {

	private String defaultColumnCondition = "";

	private String displayName = "";

	public OnetoManyFilterDefinition(final String name, final String defaultCondition,
			final String defaultColumnCondition, final Map<String, Type> parameterTypes, final String displayName) {
		super(name, defaultCondition, parameterTypes);
		this.defaultColumnCondition = defaultColumnCondition;
		this.displayName = displayName;
		// TODO Auto-generated constructor stub
	}

	public String getDefaultColumnCondition() {
		return defaultColumnCondition;
	}

	public void setDefaultColumnCondition(final String defaultColumnCondition) {
		this.defaultColumnCondition = defaultColumnCondition;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

}
