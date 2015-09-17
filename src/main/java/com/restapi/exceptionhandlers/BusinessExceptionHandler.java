package com.restapi.exceptionhandlers;

import com.restapi.exceptions.BusinessException;
import com.restapi.responser.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionHandler implements ExceptionMapper<BusinessException> {

    private static Logger logger = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    @Override
    public Response toResponse(BusinessException e) {
        logger.warn(e.getMessage());
        logger.trace(e.getMessage(), e);
        return HttpResponse.getRuntimeFAIL(e);
    }
}