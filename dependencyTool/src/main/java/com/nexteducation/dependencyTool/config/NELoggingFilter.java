package com.nexteducation.dependencyTool.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.internal.ReaderWriter;
import org.glassfish.jersey.server.ContainerRequest;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexteducation.dependencyTool.module.model.general.CustomLog;
import com.nexteducation.dependencyTool.util.DU;
import com.nexteducation.dependencyTool.util.LogMessage;
import com.nexteducation.dependencyTool.util.UUIDGenerator;

import net.logstash.log4j.data.HostData;

@Provider
@Component
public class NELoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
	@Context
	private ResourceInfo resourceInfo;

	@Value("${spring.application.name}")
	private String application;

	@Context
	private HttpServletRequest servletRequest;

	Logger APP_LOGGER = LogMessage.getLogger(NELoggingFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String hostname = new HostData().getHostName();
		Long threadId = Thread.currentThread().getId();
		Thread.currentThread().setName(application + "_" + hostname + "_" + DU.now() + "_" + threadId);
		String uuid = "";
		if ((requestContext.getHeaders().getFirst("X-NextEd-TraceId") == null
				|| requestContext.getHeaders().getFirst("X-NextEd-TraceId").trim().equals(""))
				&& application.equals("apigateway")) {
			uuid = UUIDGenerator.generateNumber();
		} else {
			uuid = requestContext.getHeaders().getFirst("X-NextEd-TraceId");
			if (uuid == null) {
				uuid = UUIDGenerator.generateNumber();
			}
		}
		MDC.put("traceId", uuid);
		requestContext.getHeaders().add("X-NextEd-TraceId", uuid);
		MDC.put("startTime", DU.now().toString());

		CustomLog logReq = new CustomLog();
		logReq.setStartTime(DU.now());
		logReq.setTimezone(Calendar.getInstance().getTimeZone().getDisplayName());
		logReq.setRequestHeaders(getRequestHeader(requestContext));
		logReq.setTraceId(uuid);
		logReq.setApplicationName(application);
		logReq.setQueryParam(getQueryParameters(requestContext));
		logReq.setPathParam(getPathParameters(requestContext));
		logReq.setMethod(requestContext.getMethod());
		logReq.setUri((requestContext.getUriInfo().getPath()));
		logReq.setRemoteIP(servletRequest.getRemoteAddr());
		if (logReq.getRequestHeaders().containsKey("Content-Type")
				|| logReq.getRequestHeaders().containsKey("content-type")) {
			String value1 = logReq.getRequestHeaders().get("Content-Type");
			String value2 = logReq.getRequestHeaders().get("content-type");
			if (value1 != null && !value1.contains("multipart/form-data")) {
				String entity = readEntityStream(requestContext);
				if (null != entity && entity.trim().length() > 0) {
					logReq.setRequestBody(readEntityStream(requestContext));
				}
			}
			if (value2 != null && !value2.contains("multipart/form-data")) {
				String entity = readEntityStream(requestContext);
				if (null != entity && entity.trim().length() > 0) {
					logReq.setRequestBody(readEntityStream(requestContext));
				}
			}

		} else {
			String entity = readEntityStream(requestContext);
			if (null != entity && entity.trim().length() > 0) {
				logReq.setRequestBody(readEntityStream(requestContext));
			}
		}

		ObjectMapper map = new ObjectMapper();

		MDC.put("customLog", map.writeValueAsString(logReq));

		APP_LOGGER.info("Request started");
	}

	private Map<String, String> getPathParameters(ContainerRequestContext requestContext) {
		Iterator iterator = requestContext.getUriInfo().getPathParameters().keySet().iterator();
		Map<String, String> map = new HashMap<String, String>();
		while (iterator.hasNext()) {
			String name = (String) iterator.next();
			List obj = requestContext.getUriInfo().getPathParameters().get(name);
			String value = null;
			if (null != obj && obj.size() > 0) {
				value = (String) obj.get(0);
			}
			map.put(name, value);
		}
		return map;
	}

	private Map<String, String> getQueryParameters(ContainerRequestContext requestContext) {
		Iterator iterator = requestContext.getUriInfo().getQueryParameters().keySet().iterator();
		Map<String, String> map = new HashMap<String, String>();
		while (iterator.hasNext()) {
			String name = (String) iterator.next();
			List obj = requestContext.getUriInfo().getQueryParameters().get(name);
			String value = null;
			if (null != obj && obj.size() > 0) {
				value = (String) obj.get(0);
			}
			map.put(name, value);
		}
		return map;
	}

	private Map<String, String> getRequestHeader(ContainerRequestContext requestContext) {
		Iterator iterator;
		Map<String, String> map = new HashMap<String, String>();
		iterator = requestContext.getHeaders().keySet().iterator();
		while (iterator.hasNext()) {
			String headerName = (String) iterator.next();
			String headerValue = requestContext.getHeaderString(headerName);
			map.put(headerName, headerValue);
		}
		return map;
	}

	private Map<String, String> getResponseHeader(ContainerResponseContext responseContext) {
		Iterator iterator;
		Map<String, String> map = new HashMap<String, String>();
		iterator = responseContext.getHeaders().keySet().iterator();
		while (iterator.hasNext()) {
			String headerName = (String) iterator.next();
			String headerValue = responseContext.getHeaderString(headerName);
			map.put(headerName, headerValue);
		}
		return map;
	}

	private String readEntityStream(ContainerRequestContext requestContext) {

		final StringBuilder builder = new StringBuilder();
		if (requestContext instanceof ContainerRequest) {
			if (requestContext.hasEntity()) {

				ContainerRequest request = (ContainerRequest) requestContext;
				request.bufferEntity();
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				final InputStream inputStream = request.getEntityStream();

				try {
					ReaderWriter.writeTo(inputStream, outStream);
					byte[] requestEntity = outStream.toByteArray();

					if (requestEntity.length == 0) {
						builder.append("");
					} else {
						builder.append(new String(requestEntity));
					}
					request.setEntityStream(new ByteArrayInputStream(requestEntity));
				} catch (IOException ex) {
					APP_LOGGER.info("Exception occurred while reading entity stream :{}", ex.getMessage());
				}
			}
		}
		return builder.toString();
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		String hostname = new HostData().getHostName();
		Long threadId = Thread.currentThread().getId();
		Thread.currentThread().setName(application + "_" + hostname + "_" + DU.now() + "_" + threadId);
		String stTime = MDC.get("startTime");
		Timestamp timestamp = java.sql.Timestamp.valueOf(stTime);

		String traceId = MDC.get("traceId");

		long executionTime = DU.now().getTime() - timestamp.getTime();
		MDC.put("processingTime", String.valueOf(executionTime));
		responseContext.getHeaders().add("X-NextEd-TraceId", MDC.get("traceId"));

		CustomLog logReq = new CustomLog();
		logReq.setStartTime(timestamp);
		logReq.setTimezone(Calendar.getInstance().getTimeZone().getDisplayName());
		logReq.setEndTime(DU.now());
		logReq.setProcessedTime(executionTime);
		logReq.setTraceId(traceId);
		logReq.setRequestHeaders(getRequestHeader(requestContext));
		logReq.setResponseHeaders(getResponseHeader(responseContext));
		logReq.setApplicationName(application);
		logReq.setQueryParam(getQueryParameters(requestContext));
		logReq.setPathParam(getPathParameters(requestContext));
		logReq.setMethod(requestContext.getMethod());
		logReq.setUri((requestContext.getUriInfo().getPath()));
		logReq.setRemoteIP(servletRequest.getRemoteAddr());

		ObjectMapper map = new ObjectMapper();

		MDC.put("customLog", map.writeValueAsString(logReq));

		APP_LOGGER.info("Request Ended");
		MDC.clear();
	}
}
