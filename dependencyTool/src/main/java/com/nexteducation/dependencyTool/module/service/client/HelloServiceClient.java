package com.nexteducation.dependencyTool.module.service.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nexteducation.dependencyTool.module.model.request.HelloRequest;
import com.nexteducation.dependencyTool.module.service.core.HelloService;
import com.nexteducation.dependencyTool.util.dependencyToolUrls;

public class HelloServiceClient implements HelloService {

	@Override
	public String sayHello(String name) {
		final String REST_URI = dependencyToolUrls.SERVER_URL + dependencyToolUrls.HELLO_URL;
		final RestTemplate restTemplate = new RestTemplate();
		final HttpEntity<HelloRequest> requestEntity = new HttpEntity<HelloRequest>(new HelloRequest(name));
		final ResponseEntity<String> responseEntity = restTemplate.exchange(REST_URI, HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<String>() {
				});
		return responseEntity.getBody();
	}
}
