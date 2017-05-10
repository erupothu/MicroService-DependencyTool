package com.nexteducation.dependencyTool.module.model.response;

/**
 * @author nitin_k
 *
 */
public class AsyncResponse {

	String id;

	String response;

	String response2;

	public String getResponse2() {
		return response2;
	}

	public void setResponse2(String response2) {
		this.response2 = response2;
	}

	public AsyncResponse(String id, String response) {
		this.id = id;
		this.response = response;
	}

	public AsyncResponse(String id, String response, String response2) {
		this.id = id;
		this.response = response;
		this.response2 = response2;
	}

	public AsyncResponse() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
