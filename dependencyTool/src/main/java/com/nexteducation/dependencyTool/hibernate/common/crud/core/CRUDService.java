package com.nexteducation.dependencyTool.hibernate.common.crud.core;

import java.util.List;

/**
 * @author ashishpratap
 *
 * @param <T>
 * @param <E>
 */
public interface CRUDService<T, E> {

	T save(T obj);

	void delete(T obj);

	T update(T obj);

	T findById(E id);

	List<T> findAll(Class<T> obj);

	int count(Class<T> obj);
}
