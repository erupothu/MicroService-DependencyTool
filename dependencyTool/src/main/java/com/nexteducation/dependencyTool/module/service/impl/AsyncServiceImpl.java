package com.nexteducation.dependencyTool.module.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nexteducation.dependencyTool.exception.dependencyToolException;
import com.nexteducation.dependencyTool.module.model.request.AsyncRequest;
import com.nexteducation.dependencyTool.module.model.response.AsyncResponse;
import com.nexteducation.dependencyTool.module.service.core.AsyncService;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author nitin_k
 *
 */
@Service
public class AsyncServiceImpl implements AsyncService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String fetchSyncGet(String url) {
		return restTemplate.getForEntity(url, String.class).getBody();
	}

	@Override
	public Observable<AsyncResponse> fetchAsyncGet(AsyncRequest request) {
		return Observable.<AsyncResponse> create(sub -> {
			try {
				RestTemplate restTemplate = new RestTemplate();
				String rest = restTemplate.getForEntity(request.getUrl(), String.class).getBody();
				sub.onNext(new AsyncResponse(request.getId(), rest));
				sub.onCompleted();
			} catch (Exception e) {
				dependencyToolException exception = new dependencyToolException("fetchAsyncGet", "A500", 500, "HTTP Client threw Exception; Please provide a valid URL", "HTTP Client threw Exception; Please provide a valid URL");
				sub.onError(exception);
			}
		}).subscribeOn(Schedulers.io());
	}

	@Override
	public Observable<String> fetchAsyncGet(String url) {
		return Observable.<String> create(sub -> {
			try {
				RestTemplate restTemplate = new RestTemplate();
				String rest = restTemplate.getForEntity(url, String.class).getBody();
				sub.onNext(rest);
				sub.onCompleted();
			} catch (Exception e) {
				dependencyToolException exception = new dependencyToolException("fetchAsyncGet", "A500", 500, "HTTP Client threw Exception; Please provide a valid URL", "HTTP Client threw Exception; Please provide a valid URL");
				sub.onError(exception);
			}
		}).subscribeOn(Schedulers.io());
	}

	@Override
	public List<AsyncResponse> fetchSeq(List<AsyncRequest> requests) {
		List<AsyncResponse> output = new ArrayList<AsyncResponse>();
		for (AsyncRequest request : requests) {
			AsyncResponse response = new AsyncResponse();
			response.setId(request.getId());
			response.setResponse(fetchSyncGet(request.getUrl()));
			output.add(response);
		}
		return output;
	}

	@Override
	public Observable<List<AsyncResponse>> fetchAsync(List<AsyncRequest> requests) {

		return Observable.from(requests).flatMap(i -> fetchAsyncGet(i)).doOnError(e -> e.printStackTrace())
				.subscribeOn(Schedulers.io()).toList();
	}

	@Override
	public Observable<List<AsyncResponse>> fetchAsyncMap(List<AsyncRequest> request) {
		return Observable.from(request).flatMap(i -> {
			Observable<String> r1 = fetchAsyncGet(i.getUrl());
			Observable<String> r2 = fetchAsyncGet(i.getUrl2());
			return Observable.zip(r1, r2, (response, response2) -> new AsyncResponse(i.getId(), response, response2));
		}).doOnError(e -> e.printStackTrace()).subscribeOn(Schedulers.io()).toList();
	}
}
