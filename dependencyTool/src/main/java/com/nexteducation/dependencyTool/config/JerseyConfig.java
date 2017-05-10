package com.nexteducation.dependencyTool.config;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.message.filtering.SelectableScopeResolver;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.ValidationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.RequestContextFilter;

import com.nexteducation.dependencyTool.exception.AppExceptionMapper;
import com.nexteducation.dependencyTool.exception.ConstraintViolationExceptionMapper;

import io.swagger.config.FilterFactory;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

//@ApplicationPath("dependencyTool")
//@Configuration
public class JerseyConfig extends ResourceConfig {

	@Autowired
	SwaggerPublicApiAccessSpecFilter filter;

	@PostConstruct
	public void init() {
		// Register components where DI is needed
		this.configureSwagger();
	}

	private void configureSwagger() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);

		BeanConfig config = new BeanConfig();
		config.setConfigId("springboot-jersey-swagger");
		config.setTitle("Spring Boot + Jersey + Swagger");
		config.setVersion("v1");
		config.setContact("NE");
		config.setSchemes(new String[] { "http", "https" });
		config.setBasePath("/dependencyTool/dependencyTool");
		config.setResourcePackage("com.nexteducation.dependencyTool.module.controller");
		config.setPrettyPrint(false);
		config.setScan(true);

		FilterFactory.setFilter(filter);
	}

	public JerseyConfig() {
		register(RequestContextFilter.class);
		packages("com.nexteducation.dependencyTool.module.controller");
		packages("com.nexteducation.dependencyTool.module.model.response");
		// register(LoggingFeature.class); // For Logging purpose; Using custom
		// logging
		register(NELoggingFilter.class);
		// register(SelectableEntityFilteringFeature.class);
		register(EntityFilteringFeature.class);
		register(SelectableConfigEntityProcessor.class);
		register(SelectableScopeResolver.class);
		register(AppExceptionMapper.class);
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		// Register your custom ExceptionMapper.
		register(ConstraintViolationExceptionMapper.class);
		// Register Bean Validation (this is optional as BV is automatically
		// registered when jersey-bean-validation is on the classpath but it's
		// good to know it's happening).
		register(ValidationFeature.class);
		register(CORSResponseFilter.class);
		// Register Annotations
		// SelectableConfigEntityProcessor.register(LargeView.class);
	}
}
