package com.nexteducation.dependencyTool.exception;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface dependencyToolClientExceptionMapper {
	dependencyToolClientExceptionMapper INSTANCE = Mappers.getMapper(dependencyToolClientExceptionMapper.class);

	ErrorResponse exceptionToResponse(dependencyToolClientException exception);

	List<ErrorResponse> exceptionToResponse(List<dependencyToolClientException> exception);

	dependencyToolClientException responseToException(ErrorResponse exception);

	List<dependencyToolClientException> responseToException(List<ErrorResponse> exception);

}
