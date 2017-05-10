package com.nexteducation.dependencyTool.exception;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.DataException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.DataIntegrityViolationException;

import com.nexteducation.dependencyTool.util.LogMessage;

@Provider
public class AppExceptionMapper implements ExceptionMapper<Exception> {

	@Autowired
	ReloadableResourceBundleMessageSource messageSource;

	@Override
	public Response toResponse(Exception exception) {
		LogMessage.error("Exception!", exception);
		exception.printStackTrace();
		dependencyToolClientExceptionBuilder builder=new dependencyToolClientExceptionBuilder();
		ErrorResponse errorResponse = null;
		if (exception instanceof dependencyToolClientException) {
			errorResponse = builder.exceptionToResponse((dependencyToolClientException) exception);
		} else if (exception instanceof ConstraintViolationException) {
			ConstraintViolationException exce = (ConstraintViolationException) exception;
			Set<ConstraintViolation<?>> set = exce.getConstraintViolations();
			dependencyToolException ex = new dependencyToolException("dependencyTool", "validationFailed", 400,
					"Provided data does not match constraints", "Validation failed.");
			for (Iterator<ConstraintViolation<?>> iterator = set.iterator(); iterator.hasNext();) {
				ConstraintViolation<?> next = iterator.next();
				String devMsg = next.getPropertyPath() + "|" + next.getMessage();
				String clientMsg = ((PathImpl) next.getPropertyPath()).getLeafNode().getName() + "|"
						+ next.getMessage();
				dependencyToolException e = new dependencyToolException("dependencyTool", "validationFailed", 400, devMsg, clientMsg);
				ex.addException(e);
			}
			errorResponse = builder.exceptionToResponse(ex);
		} else if (exception instanceof DataIntegrityViolationException) {
			dependencyToolException ex = null;
			if (exception.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				org.hibernate.exception.ConstraintViolationException e2 = ((org.hibernate.exception.ConstraintViolationException) (exception
						.getCause()));
				System.out.println(e2.getCause().getMessage());
				ex = new dependencyToolException("dependencyTool", e2.getSQLState(), 500, exception.getMessage(),
						messageSource.getMessage(e2.getConstraintName(), null, "Something goes wrong.", null));
			} else if (exception.getCause() instanceof PropertyValueException) {
				PropertyValueException e2 = ((PropertyValueException) (exception.getCause()));
				ex = new dependencyToolException("dependencyTool", "PropertyValueException", 500, e2.getPropertyName() +" | "+ e2.getEntityName() +"::"+e2.getMessage(), "Something goes wrong.");
			} else if (exception instanceof DataException) {
				DataException e2 = ((DataException) (exception.getCause()));
				ex = new dependencyToolException("dependencyTool", "DataException", 500, e2.getSQL() +" | "+ e2.getSQLState() +"::"+e2.getMessage(), "Something goes wrong.");
			}
			errorResponse = builder.exceptionToResponse(ex);
		} else {
			dependencyToolException ex = new dependencyToolException("dependencyTool", "serverInternalError", 500,
					exception.getMessage(), "Something goes wrong.");
			errorResponse = builder.exceptionToResponse(ex);
		}
		return Response.status(Status.fromStatusCode(errorResponse.getStatus())).entity(errorResponse.toString())
				.type(MediaType.APPLICATION_JSON).build();
	}

}