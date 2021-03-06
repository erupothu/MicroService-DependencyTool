package com.nexteducation.dependencyTool.module.filter.core;

import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.mapping.Property;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import com.nexteducation.dependencyTool.module.filter.hibernate.CustomFilterDefinition;
import com.nexteducation.dependencyTool.module.filter.hibernate.OnetoManyFilterDefinition;

public class FilterContainer {

	public FilterDefinition filterDefinition;

	public String filterName;

	public String conditionalOperator;

	public String displayName = null;

	@SuppressWarnings("rawtypes")
	public FilterContainer(final FilterDefinition filterDefinition, final Property property, final Class boClass) {
		this.filterDefinition = filterDefinition;
		filterName = filterDefinition.getFilterName();
		filterDefinition.getDefaultFilterCondition();
		assignConditionalOperator(filterName, property);
		boClass.getDeclaredFields();
		if (filterDefinition instanceof OnetoManyFilterDefinition) {
			displayName = ((OnetoManyFilterDefinition) filterDefinition).getDisplayName();
		} else if (filterDefinition instanceof CustomFilterDefinition) {
			displayName = ((CustomFilterDefinition) filterDefinition).getDisplayName();
			/*
			 * for (int i = 0; i < declaredFields.length; i++) { final Field
			 * field = declaredFields[i]; field.getType();
			 * if(field.getName().equals(property.getName())){
			 * if(field.isAnnotationPresent(FieldMap.class)){ final FieldMap
			 * fieldMap = field.getAnnotation(FieldMap.class);
			 * if(!fieldMap.fieldCode().equals("")) { try{ displayName =
			 * ControllerSupportMetadata.getMessage(fieldMap.fieldCode());
			 * }catch(final Exception e){ LogMessage.error(e.getMessage(), e); }
			 * } if(displayName == null){ displayName = fieldMap.fieldName(); }
			 * if(displayName == null){ displayName = property.getName(); }
			 * break; } else{ displayName = property.getName(); } } }
			 */
		} else {
			displayName = property.getName();
		}
	}

	public void assignConditionalOperator(final String filterName, final Property property) {
		final Type type = property.getType();
		if (type instanceof LongType || type instanceof IntegerType || type instanceof FloatType
				|| type instanceof DoubleType || type instanceof ShortType) {
			conditionalOperator = findIntegerOperator(filterName);
		} else if (type instanceof StringType) {
			conditionalOperator = findStringOperator(filterName);
		} else if (type instanceof DateType) {
			conditionalOperator = findDateOperator(filterName);
		} else {
			conditionalOperator = findOtherOperator(filterName);
		}
	}

	public String findIntegerOperator(final String filterName) {
		if (filterName.endsWith(FilterConstants.GREATER_THAN_EQUAL_SHORT_NAME)) {
			return FilterConstants.GREATER_THAN_EQUAL_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.LESSER_THAN_EQUAL_SHORT_NAME)) {
			return FilterConstants.LESSER_THAN_EQUAL_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.NOT_EQUALS_SHORT_NAME)) {
			return FilterConstants.NOT_EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.EQUALS_SHORT_NAME)) {
			return FilterConstants.EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.GREATER_THAN_SHORT_NAME)) {
			return FilterConstants.GREATER_THAN_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.LESSER_THAN_SHORT_NAME)) {
			return FilterConstants.LESSER_THAN_FULL_NAME;
		}
		return null;

	}

	public String findStringOperator(final String filterName) {
		if (filterName.endsWith(FilterConstants.NOT_EQUALS_SHORT_NAME)) {
			return FilterConstants.NOT_EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.EQUALS_SHORT_NAME)) {
			return FilterConstants.EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.NOT_LIKE_SHORT_NAME)) {
			return FilterConstants.NOT_LIKE_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.LIKE_SHORT_NAME)) {
			return FilterConstants.LIKE_FULL_NAME;
		}

		return null;
	}

	public String findDateOperator(final String filterName) {
		if (filterName.endsWith(FilterConstants.GREATER_THAN_EQUAL_SHORT_NAME)) {
			return FilterConstants.GREATER_THAN_EQUAL_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.LESSER_THAN_EQUAL_SHORT_NAME)) {
			return FilterConstants.LESSER_THAN_EQUAL_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.NOT_EQUALS_SHORT_NAME)) {
			return FilterConstants.NOT_EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.EQUALS_SHORT_NAME)) {
			return FilterConstants.EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.GREATER_THAN_SHORT_NAME)) {
			return FilterConstants.GREATER_THAN_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.LESSER_THAN_SHORT_NAME)) {
			return FilterConstants.LESSER_THAN_FULL_NAME;
		}
		/*
		 * else if(filterName.endsWith(FilterConstants.BETWEEN_SHORT_NAME)){
		 * return FilterConstants.BETWEEN_FULL_NAME; }
		 */
		return null;
	}

	public String findOtherOperator(final String filterName) {
		if (filterName.endsWith(FilterConstants.GREATER_THAN_EQUAL_SHORT_NAME)) {
			return FilterConstants.GREATER_THAN_EQUAL_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.LESSER_THAN_EQUAL_SHORT_NAME)) {
			return FilterConstants.LESSER_THAN_EQUAL_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.NOT_EQUALS_SHORT_NAME)) {
			return FilterConstants.NOT_EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.EQUALS_SHORT_NAME)) {
			return FilterConstants.EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.GREATER_THAN_SHORT_NAME)) {
			return FilterConstants.GREATER_THAN_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.LESSER_THAN_SHORT_NAME)) {
			return FilterConstants.LESSER_THAN_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.NOT_EQUALS_SHORT_NAME)) {
			return FilterConstants.NOT_EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.EQUALS_SHORT_NAME)) {
			return FilterConstants.EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.NOT_LIKE_SHORT_NAME)) {
			return FilterConstants.NOT_LIKE_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.LIKE_SHORT_NAME)) {
			return FilterConstants.LIKE_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.GREATER_THAN_EQUAL_SHORT_NAME)) {
			return FilterConstants.GREATER_THAN_EQUAL_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.LESSER_THAN_EQUAL_SHORT_NAME)) {
			return FilterConstants.LESSER_THAN_EQUAL_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.NOT_EQUALS_SHORT_NAME)) {
			return FilterConstants.NOT_EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.EQUALS_SHORT_NAME)) {
			return FilterConstants.EQUALS_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.GREATER_THAN_SHORT_NAME)) {
			return FilterConstants.GREATER_THAN_FULL_NAME;
		} else if (filterName.endsWith(FilterConstants.LESSER_THAN_SHORT_NAME)) {
			return FilterConstants.LESSER_THAN_FULL_NAME;
		}
		/*
		 * else if(filterName.endsWith(FilterConstants.BETWEEN_SHORT_NAME)){
		 * return FilterConstants.BETWEEN_FULL_NAME; }
		 */
		return null;
	}

	public FilterDefinition getFilterDefinition() {
		return filterDefinition;
	}

	public void setFilterDefinition(final FilterDefinition filterDefinition) {
		this.filterDefinition = filterDefinition;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(final String filterName) {
		this.filterName = filterName;
	}

	public String getConditionalOperator() {
		return conditionalOperator;
	}

	public void setConditionalOperator(final String conditionalOperator) {
		this.conditionalOperator = conditionalOperator;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

}
