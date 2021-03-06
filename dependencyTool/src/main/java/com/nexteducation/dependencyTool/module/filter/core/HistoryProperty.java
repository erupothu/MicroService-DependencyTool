package com.nexteducation.dependencyTool.module.filter.core;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface HistoryProperty {
	public abstract String fieldName() default "";
	public  abstract int order() default 0;
}
