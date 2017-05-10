package com.nexteducation.dependencyTool.module.service.impl;

import org.springframework.stereotype.Service;

import com.nexteducation.dependencyTool.module.service.core.HelloService;

@Service("helloService")
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "Hello! " + name;
	}
}
