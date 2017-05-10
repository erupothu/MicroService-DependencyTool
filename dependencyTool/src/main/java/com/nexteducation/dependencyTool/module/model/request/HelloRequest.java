package com.nexteducation.dependencyTool.module.model.request;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

import com.nexteducation.dependencyTool.annotations.validator.Password;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="HelloRequest")
public class HelloRequest {

	@Password
	@NotNull(message="Not null")
	private String name;
	
	@QueryParam("check")
	int check;

	@ApiModelProperty(allowableValues = "range[1, 5]", required = true)
	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public HelloRequest() {
	}

	public HelloRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
