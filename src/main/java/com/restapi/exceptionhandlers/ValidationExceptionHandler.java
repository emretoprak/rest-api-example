package com.restapi.exceptionhandlers;

import com.restapi.exceptions.ValidationException;
import com.restapi.responser.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {

    private static Logger logger = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    @Override
    public Response toResponse(ValidationException e) {
        logger.warn(e.getMessage());
        logger.trace(e.getMessage(), e);
        return HttpResponse.getValidationFAIL(e);
    }
}