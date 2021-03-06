package com.nexteducation.dependencyTool.module.filter.hibernate;

import java.util.Map;

import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.mapping.Property;
import org.hibernate.type.Type;

@SuppressWarnings("serial")
public class CustomFilterDefinition extends FilterDefinition {

	private String displayName = "";

	private Property property;

	/**
	 * @param name
	 * @param defaultCondition
	 * @param parameterTypes
	 * @param displayName
	 */
	public CustomFilterDefinition(final String name, final String defaultCondition,
			final Map<String, Type> parameterTypes, final String displayName, final Property property) {
		super(name, defaultCondition, parameterTypes);
		this.displayName = displayName;
		this.property = property;
		// TODO Auto-generated constructor stub
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(final Property property) {
		this.property = property;
	}
}
