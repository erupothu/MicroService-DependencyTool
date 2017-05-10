package com.nexteducation.dependencyTool.config;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@Component
public class NELogFilter implements Filter {

	@Value("${spring.application.name}")
	private String application;

	Logger APP_LOGGER = LogMessage.getLogger(NELogFilter.class.getName());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(req);
			HttpServletResponse resp = (HttpServletResponse) response;
			ResponseWrapper responseWrapper = new ResponseWrapper(resp);

			String hostname = new HostData().getHostName();
			Long threadId = Thread.currentThread().getId();
			Thread.currentThread().setName(application + "_" + hostname + "_" + DU.now() + "_" + threadId);
			String uuid = "";
			if ((requestWrapper.getHeader("X-NextEd-TraceId") == null
					|| requestWrapper.getHeader("X-NextEd-TraceId").trim().equals(""))
					&& application.equals("apigateway")) {
				uuid = UUIDGenerator.generateNumber();
			} else {
				uuid = requestWrapper.getHeader("X-NextEd-TraceId");
				if (uuid == null) {
					uuid = UUIDGenerator.generateNumber();
				}
			}

			requestWrapper.addHeader("X-NextEd-TraceId", uuid);

			CustomLog logReq = new CustomLog();
			logReq.setStartTime(DU.now());
			logReq.setTimezone(Calendar.getInstance().getTimeZone().getDisplayName());
			logReq.setRequestHeaders(getRequestHeader(req));
			logReq.setTraceId(uuid);
			logReq.setApplicationName(application);
			logReq.setQueryParam(getQueryParameters(req));
			logReq.setPathParam(getPathParameters(req));
			logReq.setMethod(req.getMethod());
			logReq.setUri(req.getRequestURI());
			logReq.setRemoteIP(req.getRemoteAddr());
			

			ObjectMapper map = new ObjectMapper();
			if (MDC.get("traceId") == null) {
				MDC.put("traceId", uuid);
				MDC.put("customLog", map.writeValueAsString(logReq));
				MDC.put("startTime", DU.now().toString());
				if (!responseWrapper.containsHeader("X-NextEd-TraceId")) {
					responseWrapper.setHeader("X-NextEd-TraceId", MDC.get("traceId"));
				}
				APP_LOGGER.info("Request started");
			}

			chain.doFilter(requestWrapper, responseWrapper);

			String hostname2 = new HostData().getHostName();
			Long threadId2 = Thread.currentThread().getId();
			Thread.currentThread().setName(application + "_" + hostname2 + "_" + DU.now() + "_" + threadId2);
			String stTime = DU.now().toString();
			if (MDC.get("startTime") != null) {
				stTime = MDC.get("startTime");

			}
			Timestamp timestamp = java.sql.Timestamp.valueOf(stTime);

			String traceId = UUIDGenerator.generateNumber();
			if (MDC.get("traceId") != null) {
				traceId = MDC.get("traceId");
			}

			long executionTime = DU.now().getTime() - timestamp.getTime();

			CustomLog logReqResp = new CustomLog();
			logReqResp.setStartTime(timestamp);
			logReqResp.setTimezone(Calendar.getInstance().getTimeZone().getDisplayName());
			logReqResp.setEndTime(DU.now());
			logReqResp.setProcessedTime(executionTime);
			logReqResp.setTraceId(traceId);
			logReqResp.setRequestHeaders(getRequestHeader(req));
			logReqResp.setResponseHeaders(getResponseHeader(resp));
			logReqResp.setApplicationName(application);
			logReqResp.setQueryParam(getQueryParameters(req));
			logReqResp.setPathParam(getPathParameters(req));
			logReqResp.setMethod(req.getMethod());
			logReqResp.setUri((req.getRequestURI()));
			logReqResp.setRemoteIP(req.getRemoteAddr());

			if (MDC.get("status") == null) {
				MDC.put("processingTime", String.valueOf(executionTime));
				MDC.put("customLog", map.writeValueAsString(logReqResp));
				MDC.put("status", "done");
				APP_LOGGER.info("Request Ended");
			}

		}

	}

	private Map<String, String> getPathParameters(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			String[] pathParts = pathInfo.split("/");
			for (int i = 1; i < pathParts.length; i++) {
				map.put("path" + (i + 1), pathParts[i]);
			}
		}
		return map;
	}

	private Map<String, String> getQueryParameters(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> parameterNames = request.getParameterNames();

		if (parameterNames != null) {
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				map.put(name, request.getParameter(name));
			}
		}
		return map;
	}

	private Map<String, String> getRequestHeader(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();

		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();
				map.put(name, request.getHeader(name));
			}
		}
		return map;
	}

	private Map<String, String> getResponseHeader(HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		Collection<String> headerNames = response.getHeaderNames();
		Iterator<String> iterator = headerNames.iterator();

		while (iterator.hasNext()) {
			String name = iterator.next();
			map.put(name, response.getHeader(name));
		}
		return map;
	}

	@Override
	public void destroy() {
		MDC.clear();

	}

}
