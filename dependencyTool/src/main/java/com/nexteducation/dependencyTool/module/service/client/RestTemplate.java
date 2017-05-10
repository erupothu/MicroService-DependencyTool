package com.nexteducation.dependencyTool.module.service.client;

import java.util.Collections;

public class RestTemplate extends org.springframework.web.client.RestTemplate {

	public RestTemplate() {
		super();
		this.setInterceptors(Collections.singletonList(new RestTemplateInterceptor()));
	}

}
