package com.nexteducation.dependencyTool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContext implements ApplicationContextAware {
	private static ApplicationContext CONTEXT;

	public void setApplicationContext(final ApplicationContext context) throws BeansException {
		CONTEXT = context;
	}

	public static <T> T getBean(Class<T> clazz) {
		return CONTEXT.getBean(clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String id) {
		return (T) CONTEXT.getBean(id);
	}
}