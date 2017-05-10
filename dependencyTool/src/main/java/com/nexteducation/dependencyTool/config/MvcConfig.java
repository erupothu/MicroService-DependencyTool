package com.nexteducation.dependencyTool.config;

import org.apache.commons.configuration.AbstractConfiguration;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.sources.DynamoDbConfigurationSource;
import com.nexteducation.dependencyTool.util.LogMessage;
import com.nexteducation.nextevent.service.core.ThriftEventProducerService;
import com.nexteducation.nextevent.service.impl.ThriftEventProducerServiceImpl;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

	// @Autowired
	// private SessionTracker sessionTracker;
	//
	// @Override
	// public void addInterceptors(InterceptorRegistry registry) {
	// registry.addInterceptor(sessionTracker).addPathPatterns("/**");
	// }

	@Value("${AWS.Access.ID}")
	private String key;

	@Value("${AWS.Secret.Key}")
	private String secret;

	@Value("${AWS.Region}")
	private String region;

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		LogMessage.info("MvcConfig.messageSource()");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:config.properties");
		messageSource.setCacheSeconds(10); // reload messages every 10 seconds
		return messageSource;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/dependencyTool/*");
		// Rest resources will be available in the path /NextCalendar/*
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		registration.setLoadOnStartup(1);
		registration.setName("jersey-servlet");
		return registration;
	}

	@Bean
	public ThriftEventProducerService thriftEventProducerService() {
		return new ThriftEventProducerServiceImpl();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public AbstractConfiguration sampleArchaiusConfiguration() throws Exception {

		AWSCredentials awsCredentials = new BasicAWSCredentials(key, secret);
		try {
			AmazonDynamoDB dynamo = new AmazonDynamoDBClient(awsCredentials);
			Region awsRegion = null;
			if (region.equals("us-west-2")) {
				awsRegion = Region.getRegion(Regions.US_WEST_2);
			} else {
				awsRegion = Region.getRegion(Regions.US_EAST_1);
			}
			dynamo.setRegion(awsRegion);

			DynamoDbConfigurationSource source = new DynamoDbConfigurationSource(dynamo);
			FixedDelayPollingScheduler scheduler = new FixedDelayPollingScheduler();
			DynamicConfiguration dynamicConfig = new DynamicConfiguration(source, scheduler);

			ConcurrentCompositeConfiguration finalConfig = new ConcurrentCompositeConfiguration();
			finalConfig.addConfiguration(dynamicConfig);
			ConfigurationManager.install(finalConfig);
			return finalConfig;
		} catch (Exception e) {
			return new ConcurrentCompositeConfiguration();
		}
	}
}