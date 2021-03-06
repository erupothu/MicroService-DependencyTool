package com.nexteducation.dependencyTool.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser implements Parser {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <E> E convertToClass(final String json, final Class clasz) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return (E) mapper.readValue(json, clasz);
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String convertToJson(final Object object) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (final JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public <E> E convertToObject(final String parserString, final Class clasz) {
		return JsonParser.convertToClass(parserString, clasz);
	}

	public String convertToType(final Object object) {
		return JsonParser.convertToJson(object);
	}
}
