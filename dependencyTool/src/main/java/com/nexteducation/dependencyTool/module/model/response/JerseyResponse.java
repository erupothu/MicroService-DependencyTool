package com.nexteducation.dependencyTool.module.model.response;

import com.nexteducation.dependencyTool.annotations.filtering.IgnorableView;

public class JerseyResponse {

	@IgnorableView
	private String code;
	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public JerseyResponse withCode(String code) {
		this.code = code;
		return this;
	}

	public JerseyResponse withMsg(String msg) {
		this.msg = msg;
		return this;
	}
}
