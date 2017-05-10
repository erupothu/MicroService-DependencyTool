package com.nexteducation.dependencyTool.module.model.general;

import java.sql.Timestamp;
import java.util.Map;

public class CustomLog {

	Long processedTime;

	String traceId;

	Timestamp startTime;

	Timestamp endTime;

	String method;

	String applicationName;

	String remoteIP;

	String timezone;

	String requestBody;

	Map<String, String> requestHeaders;

	Map<String, String> responseHeaders;

	Map<String, String> pathParam;

	Map<String, String> queryParam;

	String uri;

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public Long getProcessedTime() {
		return processedTime;
	}

	public void setProcessedTime(Long processedTime) {
		this.processedTime = processedTime;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public Map<String, String> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Map<String, String> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public Map<String, String> getPathParam() {
		return pathParam;
	}

	public void setPathParam(Map<String, String> pathParam) {
		this.pathParam = pathParam;
	}

	public Map<String, String> getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(Map<String, String> queryParam) {
		this.queryParam = queryParam;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
