package com.nexteducation.dependencyTool.module.filter.core;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface Filterable {
	public abstract String[] fieldSpecifiers() default {};

	public abstract String[] fieldNames() default {};

	public abstract String lhs() default "";
}
