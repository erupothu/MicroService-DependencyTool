package com.nexteducation.dependencyTool.jpa.listener;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexteducation.dependencyTool.AutowireHelper;
import com.nexteducation.dependencyTool.event.util.EventGeneratorService;
import com.nexteducation.dependencyTool.util.LogMessage;
import com.nexteducation.lib.event.enums.EventActionType;
import com.nexteducation.lib.event.payloads.Payload;

@Component
public class AbstractEntityListener {
	@Autowired
	private transient EventGeneratorService eventGeneratorService;

	@Autowired
	private transient ReloadableResourceBundleMessageSource configSource;

	// @Autowired
	// private transient SessionFactory sessionFactory;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@PrePersist
	void onPrePersist(Object o) {
		System.out.println("AbstractEntityListener.onPrePersist() class: " + o);
	}

	@PostPersist
	void onPostPersist(Object o) {
		AutowireHelper.autowire(this, this.eventGeneratorService, this.configSource, this.entityManagerFactory);
		System.out.println("AbstractEntityListener.onPostPersist()");
		if ("1".equalsIgnoreCase(configSource.getMessage("event.entity.listener.enable.insert", null, "0", null))) {
			if (eventGeneratorService.isEventableEntityObject(o, EventActionType.Insert)) {
				final Map<String, String> newStateMap = getObjectMap(o);
				final ObjectMapper mapper = new ObjectMapper();
				try {
					System.out.println("State:" + mapper.writeValueAsString(newStateMap));
				} catch (final JsonProcessingException e) {
					e.printStackTrace();
				}

				final Payload payload = new Payload();
				payload.setActionType(EventActionType.Insert);
				payload.setObjectName(o.getClass().getSimpleName());
				payload.setState(newStateMap);
				payload.setOldState(null);

				eventGeneratorService.generateInsertEvent(o, payload);
			}
		}
	}

	@PostLoad
	void onPostLoad(Object o) {
		AutowireHelper.autowire(this, this.eventGeneratorService, this.configSource, this.entityManagerFactory);
		System.out.println("AbstractEntityListener.onPostLoad()");
		if ("1".equalsIgnoreCase(configSource.getMessage("event.entity.listener.enable.load", null, "0", null))) {
			if (eventGeneratorService.isEventableEntityObject(o, EventActionType.Load)) {
				final Map<String, String> newStateMap = getObjectMap(o);
				final ObjectMapper mapper = new ObjectMapper();
				try {
					System.out.println("State:" + mapper.writeValueAsString(newStateMap));
				} catch (final JsonProcessingException e) {
					e.printStackTrace();
				}

				final Payload payload = new Payload();
				payload.setActionType(EventActionType.Load);
				payload.setObjectName(o.getClass().getSimpleName());
				payload.setState(newStateMap);
				payload.setOldState(null);

				eventGeneratorService.generateInsertEvent(o, payload);
			}
		}
	}

	@PreUpdate
	void onPreUpdate(Object o) {
		System.out.println("AbstractEntityListener.onPreUpdate() class: " + o);
	}

	@PostUpdate
	void onPostUpdate(Object o) {
		AutowireHelper.autowire(this, this.eventGeneratorService, this.configSource, this.entityManagerFactory);
		System.out.println("AbstractEntityListener.onPostUpdate()");
		if ("1".equalsIgnoreCase(configSource.getMessage("event.entity.listener.enable.update", null, "0", null))) {
			if (eventGeneratorService.isEventableEntityObject(o, EventActionType.Update)) {
				final Map<String, String> newStateMap = getObjectMap(o);
				final ObjectMapper mapper = new ObjectMapper();
				try {
					System.out.println("State:" + mapper.writeValueAsString(newStateMap));
				} catch (final JsonProcessingException e) {
					e.printStackTrace();
				}

				final Payload payload = new Payload();
				payload.setActionType(EventActionType.Update);
				payload.setObjectName(o.getClass().getSimpleName());
				payload.setState(newStateMap);
				payload.setOldState(null);

				eventGeneratorService.generateInsertEvent(o, payload);
			}
		}
	}

	@PreRemove
	void onPreRemove(Object o) {
		System.out.println("AbstractEntityListener.onPreRemove() class: " + o);
	}

	@PostRemove
	void onPostRemove(Object o) {
		AutowireHelper.autowire(this, this.eventGeneratorService, this.configSource, this.entityManagerFactory);
		System.out.println("AbstractEntityListener.onPostRemove()");
		if ("1".equalsIgnoreCase(configSource.getMessage("event.entity.listener.enable.delete", null, "0", null))) {
			if (eventGeneratorService.isEventableEntityObject(o, EventActionType.Delete)) {
				final Map<String, String> newStateMap = getObjectMap(o);
				final ObjectMapper mapper = new ObjectMapper();
				try {
					System.out.println("State:" + mapper.writeValueAsString(newStateMap));
				} catch (final JsonProcessingException e) {
					e.printStackTrace();
				}

				final Payload payload = new Payload();
				payload.setActionType(EventActionType.Delete);
				payload.setObjectName(o.getClass().getSimpleName());
				payload.setState(newStateMap);
				payload.setOldState(null);

				eventGeneratorService.generateInsertEvent(o, payload);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public Map<String, String> getObjectMap(final Object entity) {
		final Class clazz = entity.getClass();
		final Map<String, String> objectMap = new HashMap<String, String>();
		final java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		for (final Field f : fields) {
			if (f.isAnnotationPresent(Transient.class)) {
				continue;
			}
			try {
				final PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(entity, f.getName());
				if (descriptor == null) {
					continue;
				}

				final Method m = descriptor.getReadMethod();
				if (m == null) {
					continue;
				}
				final Class decClass = f.getType();
				final Object obj = m.invoke(entity);
				if (obj == null) {
					objectMap.put(f.getName(), null);
					continue;
				}
				if (obj.getClass().isSynthetic() || (obj instanceof String) || (obj instanceof Byte)
						|| (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)
						|| (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Boolean)
						|| (obj instanceof Character)) {
					// System.out.println(f.getName() +" = "+obj);
					objectMap.put(f.getName(), obj != null ? obj.toString() : null);
				} else {
					if (obj instanceof Collection) {
						continue;
					}
					if (obj instanceof Enum || obj instanceof Date || obj instanceof java.util.Date) {
						// System.out.println("emum="+f.getName());
						objectMap.put(f.getName(), obj != null ? obj.toString() : null);
						continue;
					}
					final Object idObj = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(obj);
					// System.out.println(f.getName() +" = "+idObj);
					objectMap.put(f.getName(), idObj != null ? idObj.toString() : null);
				}
			} catch (final Exception e) {
				LogMessage.log(e.getMessage(), e);
			}
		}
		return objectMap;
	}
}