package com.nexteducation.dependencyTool.module.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nexteducation.dependencyTool.module.repository.custom.core.SampleRepositoryCustom;

@Repository
public class SampleRepositoryImpl implements SampleRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Object> findByFilterText(String text) {
		// TODO Auto-generated method stub
		return null;
	}

}
