package com.nexteducation.dependencyTool.config;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.glassfish.jersey.message.filtering.SelectableScopeResolver;
import org.glassfish.jersey.message.filtering.spi.AbstractEntityProcessor;
import org.glassfish.jersey.message.filtering.spi.EntityGraph;
import org.glassfish.jersey.message.filtering.spi.EntityProcessor;

import com.nexteducation.dependencyTool.annotations.filtering.IgnorableView;

import jersey.repackaged.com.google.common.collect.Sets;

public class SelectableConfigEntityProcessor extends AbstractEntityProcessor {

	private static Set<Class> registerAnnotationClasses = new HashSet<Class>();

	public static void register(Class clazz) {
		registerAnnotationClasses.add(clazz);
	}

	public static boolean unRegister(Class clazz) {
		return registerAnnotationClasses.remove(clazz);
	}

	@Override
	protected Result process(final String fieldName, final Class<?> fieldClass, final Annotation[] fieldAnnotations,
			final Annotation[] annotations, final EntityGraph graph) {

		if (fieldName != null) {
			final Set<String> scopes = Sets.newHashSet();

			boolean annotationFound = false;
			for (int i = 0; i < fieldAnnotations.length; i++) {
				Annotation annotation = fieldAnnotations[i];
				if (annotation instanceof IgnorableView) {
					annotationFound = true;
				}
				if (registerAnnotationClasses.contains(annotation.annotationType())) {
					annotationFound = true;
				}
			}
			if (!annotationFound) {
				// add default selectable scope in case of none requested
				scopes.add(SelectableScopeResolver.DEFAULT_SCOPE);
			}

			// add specific scope in case of specific request
			scopes.add(SelectableScopeResolver.PREFIX + fieldName);

			addFilteringScopes(fieldName, fieldClass, scopes, graph);
		}

		return EntityProcessor.Result.APPLY;
	}
}
