package com.nexteducation.dependencyTool.hibernate.listener;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.mapping.Bag;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.proxy.LazyInitializer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexteducation.dependencyTool.event.util.EventGeneratorService;
import com.nexteducation.dependencyTool.util.LogMessage;
import com.nexteducation.lib.event.enums.EventActionType;
import com.nexteducation.lib.event.payloads.Payload;

/**
 * @author ashishpratap
 *
 */
@Component
public class EventableEntityListener
		implements PostDeleteEventListener, PostInsertEventListener, PostUpdateEventListener, PostLoadEventListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EventGeneratorService eventGeneratorService;

	@Autowired
	private transient ReloadableResourceBundleMessageSource config;

	@Override
	public void onPostUpdate(final PostUpdateEvent event) {
		System.out.println("EventableEntityListener.onPostUpdate()");
		if ("1".equalsIgnoreCase(config.getMessage("event.entity.listener.enable.update", null, "0", null))) {
			if (eventGeneratorService.isEventableEntityObject(event.getEntity(), EventActionType.Update)) {
				final Map<String, String> newStateMap = getObjectMap(event.getEntity(), event.getSession());
				final ObjectMapper mapper = new ObjectMapper();
				try {
					System.out.println("State:" + mapper.writeValueAsString(newStateMap));
				} catch (final JsonProcessingException e) {
					e.printStackTrace();
				}

				final Object[] oldState = event.getOldState();
				final String[] propertyNames = event.getPersister().getPropertyNames();
				final Map<String, String> propertiesChangeMap = new HashMap<String, String>();

				if (event.getDirtyProperties() != null) {
					for (final int i : event.getDirtyProperties()) {
						final String propertyName = propertyNames[i];
						if (skipLogForProperty(propertyName)) {
							continue;
						}
						propertiesChangeMap.put(propertyName,
								oldState[i] == null ? "null" : getValueOfProperty(oldState[i], event.getSession()));
					}
				}

				final Payload payload = new Payload();
				payload.setActionType(EventActionType.Update);
				payload.setObjectName(event.getEntity().getClass().getSimpleName());
				payload.setState(newStateMap);
				payload.setOldState(propertiesChangeMap);

				eventGeneratorService.generateUpdateEvent(event.getEntity(), payload);
			}
		}
	}

	@Override
	public void onPostInsert(final PostInsertEvent event) {
		System.out.println("EventableEntityListener.onPostInsert()");
		if ("1".equalsIgnoreCase(config.getMessage("event.entity.listener.enable.insert", null, "0", null))) {
			if (eventGeneratorService.isEventableEntityObject(event.getEntity(), EventActionType.Insert)) {
				final Map<String, String> newStateMap = getObjectMap(event.getEntity(), event.getSession());
				final ObjectMapper mapper = new ObjectMapper();
				try {
					System.out.println("State:" + mapper.writeValueAsString(newStateMap));
				} catch (final JsonProcessingException e) {
					e.printStackTrace();
				}

				final Payload payload = new Payload();
				payload.setActionType(EventActionType.Insert);
				payload.setObjectName(event.getEntity().getClass().getSimpleName());
				payload.setState(newStateMap);
				payload.setOldState(null);

				eventGeneratorService.generateInsertEvent(event.getEntity(), payload);
			}
		}
	}

	@Override
	public void onPostDelete(final PostDeleteEvent event) {
		System.out.println("EventableEntityListener.onPostDelete()");
		if ("1".equalsIgnoreCase(config.getMessage("event.entity.listener.enable.delete", null, "0", null))) {
			if (eventGeneratorService.isEventableEntityObject(event.getEntity(), EventActionType.Delete)) {
				final Map<String, String> newStateMap = getObjectMap(event.getEntity(), event.getSession());
				final ObjectMapper mapper = new ObjectMapper();
				try {
					System.out.println("State:" + mapper.writeValueAsString(newStateMap));
				} catch (final JsonProcessingException e) {
					e.printStackTrace();
				}

				final Payload payload = new Payload();
				payload.setActionType(EventActionType.Delete);
				payload.setObjectName(event.getEntity().getClass().getSimpleName());
				payload.setState(newStateMap);
				payload.setOldState(null);

				eventGeneratorService.generateDeleteEvent(event.getEntity(), payload);
			}
		}
	}

	@Override
	public void onPostLoad(final PostLoadEvent event) {
		System.out.println("EventableEntityListener.onPostLoad()");
		if ("1".equalsIgnoreCase(config.getMessage("event.entity.listener.enable.load", null, "0", null))) {
			if (eventGeneratorService.isEventableEntityObject(event.getEntity(), EventActionType.Load)) {
				final Map<String, String> newStateMap = getObjectMap(event.getEntity(), event.getSession());
				final ObjectMapper mapper = new ObjectMapper();
				try {
					System.out.println("State:" + mapper.writeValueAsString(newStateMap));
				} catch (final JsonProcessingException e) {
					e.printStackTrace();
				}

				final Payload payload = new Payload();
				payload.setActionType(EventActionType.Load);
				payload.setObjectName(event.getEntity().getClass().getSimpleName());
				payload.setState(newStateMap);
				payload.setOldState(null);

				eventGeneratorService.generateLoadEvent(event.getEntity(), payload);
			}
		}
	}

	private boolean skipLogForProperty(final String propertyName) {
		boolean skip = false;
		if (propertyName != null && !propertyName.isEmpty()) {
			if (propertyName.equalsIgnoreCase("modifiedBy") || propertyName.equalsIgnoreCase("modifiedOn")) {
				skip = true;
			}
		}
		return skip;
	}

	@SuppressWarnings("rawtypes")
	private String getValueOfProperty(final Object obj, final Session session) {
		String value = "";
		if (BeanUtils.isSimpleProperty(obj.getClass())) {
			value = String.valueOf(obj);
		}
		if (obj instanceof Collection) {
			final Collection colle = (Collection) obj;
			value = String.valueOf(colle.size());
		} else if (obj instanceof Bag) {
			final Bag b = (Bag) obj;
			value = String.valueOf(b.getBatchSize());
		} else {
			value = getValueOfBeanProperty(obj, session);
		}
		return value;
	}

	private String getValueOfBeanProperty(final Object obj, final Session session) {
		String value = "";
		final ClassMetadata metaData = session.getSessionFactory()
				.getClassMetadata(HibernateProxyHelper.getClassWithoutInitializingProxy(obj));
		if (metaData == null) {
			value = String.valueOf(obj);
		} else {
			final String identifierName = metaData.getIdentifierPropertyName();
			value = getIdentifierValue(obj, identifierName);
		}
		return value;
	}

	private String getIdentifierValue(final Object entity, final String identifierPropName) {
		String idVal = "";
		try {
			idVal = String.valueOf(PropertyUtils.getProperty(entity, identifierPropName));
		} catch (final Exception e) {
			LogMessage.error("Unable to find Identifier value ", e);
		}
		return idVal;
	}

	@SuppressWarnings("rawtypes")
	public Map<String, String> getObjectMap(final Object entity, final Session session) {
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
				final SessionFactory sessionFactory = session.getSessionFactory();
				ClassMetadata meta = null;
				if (!decClass.isPrimitive() && !decClass.isEnum() && !decClass.isArray()) {
					meta = sessionFactory.getClassMetadata(decClass);
				}

				if (meta != null) {
					if (obj instanceof HibernateProxy) {
						final HibernateProxy proxy = (HibernateProxy) obj;
						final LazyInitializer li = proxy.getHibernateLazyInitializer();
						final Object idObj = li.getIdentifier();
						objectMap.put(f.getName(), idObj != null ? idObj.toString() : null);
					} else {

						final String idProp = meta.getIdentifierPropertyName();
						final PropertyDescriptor desc = PropertyUtils.getPropertyDescriptor(obj, idProp);
						final Method readMethod = desc.getReadMethod();
						final Object data = readMethod.invoke(obj);

						objectMap.put(f.getName(), data != null ? data.toString() : null);
					}
				} else {
					objectMap.put(f.getName(), obj != null ? obj.toString() : null);
				}
			} catch (final Exception e) {
				LogMessage.log(e.getMessage(), e);
			}
		}
		return objectMap;
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
