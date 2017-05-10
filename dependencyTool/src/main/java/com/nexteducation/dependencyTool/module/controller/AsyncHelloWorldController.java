package com.nexteducation.dependencyTool.module.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.nexteducation.dependencyTool.module.model.request.AsyncRequest;
import com.nexteducation.dependencyTool.module.model.response.AsyncResponse;
import com.nexteducation.dependencyTool.module.service.core.AsyncService;
import com.nexteducation.dependencyTool.util.dependencyToolUrls;

import rx.Observable;

/**
 * @author nitin_k
 *
 */
@RestController
public class AsyncHelloWorldController {

	@Autowired
	private AsyncService asyncService;

	@RequestMapping(value = dependencyToolUrls.RX_SEQ, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<AsyncResponse> parallelSeq(@RequestBody final List<AsyncRequest> request) {

		// TEST-URL
		// String url =
		// "https://app2.nexterp.in/NextSyllabus/nextsyllabus/getBoard/2";
		// System.out.println(DynamicPropertyFactory.getInstance().getStringProperty("test",
		// "").get());
		return asyncService.fetchSeq(request);
	}

	@RequestMapping(value = dependencyToolUrls.RX, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public DeferredResult<List<AsyncResponse>> parallelRx(@RequestBody final List<AsyncRequest> request) {

		// TEST-URL
		// String url =
		// "https://app2.nexterp.in/NextSyllabus/nextsyllabus/getBoard/2";
		DeferredResult<List<AsyncResponse>> deffered = new DeferredResult<List<AsyncResponse>>();
		Observable<List<AsyncResponse>> response = (asyncService.fetchAsync(request));
		// response.toBlocking(); to convert to sync
		response.subscribe(m -> deffered.setResult(m));
		return deffered;
	}

	@RequestMapping(value = dependencyToolUrls.RX_MAP, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public DeferredResult<List<AsyncResponse>> parallelRxMap(@RequestBody final List<AsyncRequest> request) {

		// TEST-URL
		// String url =
		// "https://app2.nexterp.in/NextSyllabus/nextsyllabus/getBoard/2";
		DeferredResult<List<AsyncResponse>> deffered = new DeferredResult<List<AsyncResponse>>();
		Observable<List<AsyncResponse>> response = (asyncService.fetchAsyncMap(request));
		// response.toBlocking(); to convert to sync
		response.subscribe(m -> deffered.setResult(m));
		return deffered;
	}

}
