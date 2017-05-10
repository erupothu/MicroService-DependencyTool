package com.nexteducation.dependencyTool.exception;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-10T15:51:45+0530",
    comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_60 (Oracle Corporation)"
)
@Component
public class dependencyToolClientExceptionMapperImpl implements dependencyToolClientExceptionMapper {

    @Override
    public ErrorResponse exceptionToResponse(dependencyToolClientException exception) {
        if ( exception == null ) {
            return null;
        }

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setDomain( exception.getDomain() );
        errorResponse.setCode( exception.getCode() );
        errorResponse.setStatus( exception.getStatus() );
        errorResponse.setDebugMessage( exception.getDebugMessage() );
        errorResponse.setClientMessage( exception.getClientMessage() );
        errorResponse.setMoreInfoLink( exception.getMoreInfoLink() );
        errorResponse.setErrors( exceptionToResponse( exception.getErrors() ) );

        return errorResponse;
    }

    @Override
    public List<ErrorResponse> exceptionToResponse(List<dependencyToolClientException> exception) {
        if ( exception == null ) {
            return null;
        }

        List<ErrorResponse> list_ = new ArrayList<ErrorResponse>();
        for ( dependencyToolClientException dependencyToolClientException : exception ) {
            list_.add( exceptionToResponse( dependencyToolClientException ) );
        }

        return list_;
    }

    @Override
    public dependencyToolClientException responseToException(ErrorResponse exception) {
        if ( exception == null ) {
            return null;
        }

        dependencyToolClientException dependencyToolClientException = new dependencyToolClientException();

        dependencyToolClientException.setDomain( exception.getDomain() );
        dependencyToolClientException.setCode( exception.getCode() );
        dependencyToolClientException.setStatus( exception.getStatus() );
        dependencyToolClientException.setDebugMessage( exception.getDebugMessage() );
        dependencyToolClientException.setClientMessage( exception.getClientMessage() );
        dependencyToolClientException.setMoreInfoLink( exception.getMoreInfoLink() );
        dependencyToolClientException.setErrors( responseToException( exception.getErrors() ) );

        return dependencyToolClientException;
    }

    @Override
    public List<dependencyToolClientException> responseToException(List<ErrorResponse> exception) {
        if ( exception == null ) {
            return null;
        }

        List<dependencyToolClientException> list_ = new ArrayList<dependencyToolClientException>();
        for ( ErrorResponse errorResponse : exception ) {
            list_.add( responseToException( errorResponse ) );
        }

        return list_;
    }
}
