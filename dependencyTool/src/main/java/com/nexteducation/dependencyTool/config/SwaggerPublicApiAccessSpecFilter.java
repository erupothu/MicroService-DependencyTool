package com.nexteducation.dependencyTool.config;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.swagger.core.filter.SwaggerSpecFilter;
import io.swagger.model.ApiDescription;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;

@Component
public class SwaggerPublicApiAccessSpecFilter implements SwaggerSpecFilter {

	public static final String PUBLIC = "public";

	@Override
	public boolean isOperationAllowed(Operation operation, ApiDescription api, Map<String, List<String>> params,
			Map<String, String> cookies, Map<String, List<String>> headers) {
		// Return false for operations that don't have the PUBLIC access
		// implicit param

		if (params.containsKey("access") && !params.get("access").isEmpty()
				&& params.get("access").get(0).equals("private")) {
			return true;
		} else {
			List<Parameter> param = operation.getParameters();
			for (Parameter p : param) {
				if (p.getAccess() != null && p.getAccess().equals(PUBLIC) && p.getName().equals("swaggerFilter")) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isParamAllowed(Parameter parameter, Operation operation, ApiDescription api,
			Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
		// Remove the public access implicit param
		return parameter.getAccess() == null || !parameter.getAccess().equals(PUBLIC);
	}

	@Override
	public boolean isPropertyAllowed(Model model, Property property, String propertyName,
			Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
		return true;
	}
}