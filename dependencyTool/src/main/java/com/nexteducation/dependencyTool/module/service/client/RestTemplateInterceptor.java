package com.nexteducation.dependencyTool.module.service.client;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		HttpHeaders headers = request.getHeaders();
		if (MDC.get("traceId") != null) {
			headers.add("X-NextEd-TraceId", MDC.get("traceId"));
		}
		return execution.execute(request, body);
	}
}