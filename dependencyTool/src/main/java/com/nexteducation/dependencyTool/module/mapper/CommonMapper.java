package com.nexteducation.dependencyTool.module.mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.mapstruct.BeforeMapping;
import org.springframework.beans.BeanUtils;

import com.nexteducation.dependencyTool.hibernate.common.HibernateUtility;

/**
 * @author sarats
 */

public class CommonMapper {

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@BeforeMapping
	public void beforePropertySet(final Object sourceObj) {
		if (sourceObj != null) {
			try {
				final Class clazz = sourceObj.getClass();
				final Field[] fields = clazz.getDeclaredFields();
				for (final Field field : fields) {
					if (BeanUtils.isSimpleValueType(field.getType())) {
						continue;
					}

					final PropertyDescriptor mappedDescriptor = PropertyUtils.getPropertyDescriptor(sourceObj,
							field.getName());
					if (mappedDescriptor != null) {
						final Method m1 = mappedDescriptor.getReadMethod();
						final Object obj = m1.invoke(sourceObj);
						if (HibernateUtility.isProxy(obj)) {
							final Method m2 = mappedDescriptor.getWriteMethod();
							// m2.invoke(sourceObj, null);
							field.setAccessible(true);
							field.set(sourceObj, null);
							field.setAccessible(false);
						}
					}

					if (Collection.class.isAssignableFrom(field.getType())) {
						field.setAccessible(true);
						final Collection<Object> list = (Collection<Object>) field.get(sourceObj);
						if (list != null) {
							if (!Hibernate.isInitialized(list)) {
								// final PropertyDescriptor mappedDescrip =
								// PropertyUtils.getPropertyDescriptor(sourceObj,
								// field.getName());
								final Method m2 = mappedDescriptor.getWriteMethod();
								field.setAccessible(true);
								field.set(sourceObj, null);
								field.setAccessible(false);
							}
						}
						field.setAccessible(false);
						continue;
					}

				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}
}