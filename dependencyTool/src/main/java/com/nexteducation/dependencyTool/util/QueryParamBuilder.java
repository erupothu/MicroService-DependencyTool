package com.nexteducation.dependencyTool.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.web.util.UriComponentsBuilder;

public class QueryParamBuilder {

	@SuppressWarnings({ "unchecked", "unused" })
	public <T, S> URI build(Object object, String restUri) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restUri);
		if (object == null) {
			return builder.build().encode().toUri();
		}
		for (Field field : object.getClass().getDeclaredFields()) {
			QueryParam[] annotations = field.getDeclaredAnnotationsByType(QueryParam.class);
			if (annotations.length > 0) {
				try {
					Object first = new PropertyDescriptor(field.getName(), object.getClass()).getReadMethod()
							.invoke(object);
					Class<T> type = (Class<T>) field.getType();
					if (first == null) {
						continue;
					}
					first = (T) first;
					if (first instanceof Collection) {
						for (Iterator<S> iterator = ((Collection<S>) first).iterator(); iterator.hasNext();) {
							S value = (S) iterator.next();
							builder.queryParam(annotations[0].value(), value);
						}
					} else {
						builder.queryParam(annotations[0].value(), first);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (IntrospectionException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.build().encode().toUri();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public <T, S> URI build(List<Object> objects, String restUri) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restUri);
		for (Object object : objects) {
			if (object == null) {
				continue;
			}
			for (Field field : object.getClass().getDeclaredFields()) {
				QueryParam[] annotations = field.getDeclaredAnnotationsByType(QueryParam.class);
				if (annotations.length > 0) {
					try {
						Object first = new PropertyDescriptor(field.getName(), object.getClass()).getReadMethod()
								.invoke(object);
						Class<T> type = (Class<T>) field.getType();
						if (first == null) {
							continue;
						}
						first = (T) first;
						if (first instanceof Collection) {
							for (Iterator<S> iterator = ((Collection<S>) first).iterator(); iterator.hasNext();) {
								S value = (S) iterator.next();
								builder.queryParam(annotations[0].value(), value);
							}
						} else {
							builder.queryParam(annotations[0].value(), first);
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (IntrospectionException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return builder.build().encode().toUri();
	}

	/*		public static void main(String[] args) {
				StudentComplexFetchParam studentComplexFetchParam = new StudentComplexFetchParam();
				studentComplexFetchParam.setFetch(Arrays.asList("contacts", "Parents"));
				studentComplexFetchParam.setSectionIds(
						Arrays.asList("27328c7b-6815-444e-949a-19dc3315d276", "27328c7b-6815-444e-949a-19dc3315d276"));
				SessionParams sessionParams = new SessionParams();
				sessionParams.setAcademicSessionId("27328c7b-6815-444e-949a-19dc3315d276");
				URI REST_URI = build(Arrays.asList(studentComplexFetchParam,sessionParams), "http://locahost:0890/");
				System.out.println(REST_URI);
			}*/
}
