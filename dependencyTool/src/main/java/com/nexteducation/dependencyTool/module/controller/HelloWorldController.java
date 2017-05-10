package com.nexteducation.dependencyTool.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nexteducation.dependencyTool.module.model.request.HelloRequest;
import com.nexteducation.dependencyTool.module.service.core.HelloService;
import com.nexteducation.dependencyTool.util.dependencyToolUrls;

@Controller
public class HelloWorldController {

	@Autowired
	HelloService helloService;

	/*@RequestMapping(dependencyToolUrls.HELLO_URL)
	@ResponseBody
	public String sayHello(@RequestBody HelloRequest helloRequest) throws Exception {
		if (!valid(helloRequest)) {
			throw new Exception("HelloRequest Object is not correct.");
		}
		return helloService.sayHello(helloRequest.getName());
	}*/
	@RequestMapping(dependencyToolUrls.HELLO_URL)
	@ResponseBody
	public String sayHello() throws Exception {
		return "HI THERE FROM DEPendency TOOL";
	}
	private boolean valid(HelloRequest helloRequest) {
		if (helloRequest == null || helloRequest.getName() == null) {
			return false;
		}
		return true;
	}

}
