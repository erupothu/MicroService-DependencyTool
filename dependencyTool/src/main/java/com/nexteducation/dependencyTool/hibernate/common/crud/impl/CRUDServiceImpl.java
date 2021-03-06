package com.nexteducation.dependencyTool.hibernate.common.crud.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nexteducation.dependencyTool.hibernate.common.crud.core.CRUDService;

/**
 * @author ashishpratap
 *
 * @param <T>
 * @param <E>
 */
public class CRUDServiceImpl<T, E> implements CRUDService<T, E> {

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T save(final T obj) {
		this.getSession().save(obj);
		return obj;
	}

	@Override
	public void delete(final T obj) {
		this.getSession().delete(obj);
	}

	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public T update(final T obj) {
		getSession().update(obj);
		return obj;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findById(final E id) {
		final Class<T> typeOfT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return (T) getSession().get(typeOfT, (Serializable) id);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(final Class<T> obj) {
		return getSession().createCriteria(obj).list();
	}

	@Override
	public int count(final Class<T> obj) {
		return getSession().createCriteria(obj).list().size();
	}

}
