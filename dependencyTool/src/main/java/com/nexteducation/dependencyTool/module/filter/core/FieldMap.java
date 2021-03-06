package com.nexteducation.dependencyTool.module.filter.core;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface FieldMap {
	public abstract String fieldCode() default "";
	public abstract String fieldName() default "";
}
