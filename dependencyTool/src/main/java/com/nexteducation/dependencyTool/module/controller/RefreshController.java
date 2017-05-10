package com.nexteducation.dependencyTool.module.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nexteducation.dependencyTool.util.dependencyToolUrls;

@RefreshScope
@Component
@RestController
public class RefreshController {

	@RequestMapping(value = dependencyToolUrls.REFRESH, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String refresh() {
		return "OK";
	}
}
