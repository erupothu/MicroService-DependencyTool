package com.nexteducation.dependencyTool.module.repository.custom.core;

import java.util.List;

public interface SampleRepositoryCustom {
	List<Object> findByFilterText(String text);
}
