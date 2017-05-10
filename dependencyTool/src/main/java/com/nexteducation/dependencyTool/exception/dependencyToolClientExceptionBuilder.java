package com.nexteducation.dependencyTool.exception;

import java.util.ArrayList;
import java.util.List;

public class dependencyToolClientExceptionBuilder {

	public ErrorResponse exceptionToResponse(dependencyToolClientException exception) {
		if (exception == null) {
			return null;
		}

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setDomain(exception.getDomain());
		errorResponse.setCode(exception.getCode());
		errorResponse.setStatus(exception.getStatus());
		errorResponse.setDebugMessage(exception.getDebugMessage());
		errorResponse.setClientMessage(exception.getClientMessage());
		errorResponse.setMoreInfoLink(exception.getMoreInfoLink());
		errorResponse.setErrors(exceptionToResponse(exception.getErrors()));

		return errorResponse;
	}

	public List<ErrorResponse> exceptionToResponse(List<dependencyToolClientException> exception) {
		if (exception == null) {
			return null;
		}

		List<ErrorResponse> list_ = new ArrayList<ErrorResponse>();
		for (dependencyToolClientException nextServiceClientException : exception) {
			list_.add(exceptionToResponse(nextServiceClientException));
		}

		return list_;
	}

	public dependencyToolClientException responseToException(ErrorResponse exception) {
		if (exception == null) {
			return null;
		}

		dependencyToolClientException nextServiceClientException = new dependencyToolClientException();

		nextServiceClientException.setDomain(exception.getDomain());
		nextServiceClientException.setCode(exception.getCode());
		nextServiceClientException.setStatus(exception.getStatus());
		nextServiceClientException.setDebugMessage(exception.getDebugMessage());
		nextServiceClientException.setClientMessage(exception.getClientMessage());
		nextServiceClientException.setMoreInfoLink(exception.getMoreInfoLink());
		nextServiceClientException.setErrors(responseToException(exception.getErrors()));

		return nextServiceClientException;
	}

	public List<dependencyToolClientException> responseToException(List<ErrorResponse> exception) {
		if (exception == null) {
			return null;
		}

		List<dependencyToolClientException> list_ = new ArrayList<dependencyToolClientException>();
		for (ErrorResponse errorResponse : exception) {
			list_.add(responseToException(errorResponse));
		}

		return list_;
	}
}
