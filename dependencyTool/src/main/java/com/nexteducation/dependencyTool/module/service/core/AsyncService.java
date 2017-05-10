package com.nexteducation.dependencyTool.module.service.core;

import java.util.List;

import com.nexteducation.dependencyTool.module.model.request.AsyncRequest;
import com.nexteducation.dependencyTool.module.model.response.AsyncResponse;

import rx.Observable;

/**
 * @author nitin_k
 *
 */
public interface AsyncService {

	String fetchSyncGet(String url);
	
	Observable<AsyncResponse> fetchAsyncGet(AsyncRequest request);

	List<AsyncResponse> fetchSeq(List<AsyncRequest> request);
	
	
	Observable<List<AsyncResponse>> fetchAsync(List<AsyncRequest> request);
	
	Observable<List<AsyncResponse>> fetchAsyncMap(List<AsyncRequest> request);

	Observable<String> fetchAsyncGet(String url);
}
