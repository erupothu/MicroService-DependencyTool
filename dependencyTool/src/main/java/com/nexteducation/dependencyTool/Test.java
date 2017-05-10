package com.nexteducation.dependencyTool;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nexteducation.dependencyTool.exception.dependencyToolExceptionHandler;
import com.nexteducation.dependencyTool.module.model.request.HelloRequest;
import com.nexteducation.dependencyTool.module.model.response.JerseyResponse;

public class Test {

	public static void main(String[] args) {
		final String REST_URI = "http://localhost:8082/dependencyTool/dependencyTool/jersey/hello/123?page=1&sort_by=-field,field2";
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new dependencyToolExceptionHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final HttpEntity<HelloRequest> requestEntity = new HttpEntity<HelloRequest>(new HelloRequest("Ashish"),headers);
		final ResponseEntity<JerseyResponse> responseEntity = restTemplate.exchange(REST_URI, HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<JerseyResponse>() {
				});
		JerseyResponse response=responseEntity.getBody();
		System.out.println(response.getCode()+","+response.getMsg());
	}
}
