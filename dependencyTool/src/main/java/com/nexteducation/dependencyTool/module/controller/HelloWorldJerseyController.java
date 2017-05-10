package com.nexteducation.dependencyTool.module.controller;

import java.lang.annotation.Annotation;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nexteducation.dependencyTool.annotations.filtering.IgnorableView;
import com.nexteducation.dependencyTool.exception.dependencyToolException;
import com.nexteducation.dependencyTool.module.filter.queryable.QueryableParams;
import com.nexteducation.dependencyTool.module.model.params.SampleContextParams;
import com.nexteducation.dependencyTool.module.model.params.SessionParams;
import com.nexteducation.dependencyTool.module.model.request.HelloRequest;
import com.nexteducation.dependencyTool.module.model.response.JerseyResponse;
import com.nexteducation.dependencyTool.util.dependencyToolUrls;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path(dependencyToolUrls.JERSEY)
@Api(value="HelloWorldJerseyController")
public class HelloWorldJerseyController {

	@ApiOperation(value = "sayHello")
	// @ApiOperation(value="sayHello",hidden=true)
	// use to hide from swagger!
	@POST
	@Path(dependencyToolUrls.JERSEY_HELLO_URL)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/*@ApiImplicitParams({
			@ApiImplicitParam(name = "swaggerFilter", required = true, dataType = "string", access = SwaggerPublicApiAccessSpecFilter.PUBLIC, paramType = "header") })*/
	//use to make this API visible publicly
	public Response sayHello(@BeanParam SampleContextParams contextParams, @BeanParam SessionParams sessionParams,
			@BeanParam QueryableParams queryable, @Valid HelloRequest helloRequest) throws dependencyToolException {
		if (!valid(helloRequest)) {
			throw new dependencyToolException("fetchAsyncGet", "requestFoundNull", 403,
					"HelloRequest Object is not correct.", "HelloRequest Object is not correct.");
		}
		System.out.println(contextParams);
		System.out.println(sessionParams);
		System.out.println(queryable);
		System.out.println(queryable != null ? queryable.build() : "");

		if (contextParams.getUuid() == null) {
			throw new dependencyToolException("fetchAsyncGet", "A500", 500, "Uuid is invalid", "Uuid is invalid");
		}
		JerseyResponse jerseyResponse = new JerseyResponse().withCode("Success").withMsg(helloRequest.getName());
		return Response.ok().entity(jerseyResponse, new Annotation[] { IgnorableView.Factory.get() }).build();
		// return jerseyResponse;
	}

	private boolean valid(HelloRequest helloRequest) {
		if (helloRequest == null || helloRequest.getName() == null) {
			return false;
		}
		return true;
	}
}
