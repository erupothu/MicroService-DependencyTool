package com.nexteducation.dependencyTool.hibernate;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import com.nexteducation.dependencyTool.util.ServiceLogger;

public class LogInterceptor extends EmptyInterceptor {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String MODIFIED_BY_VARIABLE = "modifiedBy";
	public static final String CREATED_BY_VARIABLE = "createdBy";
	public static final String MODIFIED_ON_VARIABLE = "modifiedOn";
	public static final String CREATED_ON_VARIABLE = "createdOn";

	@Override
	public boolean onSave(final Object entity, final Serializable id, final Object[] state,
			final String[] propertyNames, final Type[] types) {
		return false;
	}

	@Override
	public void afterTransactionCompletion(final Transaction tx) {
		super.afterTransactionCompletion(tx);
	}

	@Override
	public boolean onFlushDirty(final Object entity, final Serializable id, final Object[] currentState,
			final Object[] previousState, final String[] propertyNames, final Type[] types) {
		return false;
	}

	public boolean canSkipCreatedOnAudit(final Object entity) {
		boolean canSkipCreatedOnAudit = false;
		if (entity != null) {
			final Field[] fields = entity.getClass().getDeclaredFields();
			for (final Field field : fields) {
				if (field.getName().equalsIgnoreCase("skipCreatedOnAudit")) {
					try {
						canSkipCreatedOnAudit = (Boolean) PropertyUtils.getProperty(entity, field.getName());
					} catch (final IllegalArgumentException e) {
						ServiceLogger.error(e.getMessage(), e);
					} catch (final IllegalAccessException e) {
						ServiceLogger.error(e.getMessage(), e);
					} catch (final InvocationTargetException e) {
						ServiceLogger.error(e.getMessage(), e);
					} catch (final NoSuchMethodException e) {
						ServiceLogger.error(e.getMessage(), e);
					}
					break;
				}
			}
		}
		return canSkipCreatedOnAudit;
	}

	public boolean canSkipUserAudit(final Object entity) {
		boolean canSkipUserAudit = false;
		if (entity != null) {
			final Field[] fields = entity.getClass().getDeclaredFields();
			for (final Field field : fields) {
				if (field.getName().equalsIgnoreCase("skipUserAudit")) {
					try {
						canSkipUserAudit = (Boolean) PropertyUtils.getProperty(entity, field.getName());
					} catch (final IllegalArgumentException e) {
						ServiceLogger.error(e.getMessage(), e);
					} catch (final IllegalAccessException e) {
						ServiceLogger.error(e.getMessage(), e);
					} catch (final InvocationTargetException e) {
						ServiceLogger.error(e.getMessage(), e);
					} catch (final NoSuchMethodException e) {
						ServiceLogger.error(e.getMessage(), e);
					}
					break;
				}
			}
		}
		return canSkipUserAudit;
	}
}
