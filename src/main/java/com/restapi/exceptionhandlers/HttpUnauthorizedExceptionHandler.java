package com.restapi.exceptionhandlers;

import com.restapi.responser.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.ws.http.HTTPException;

@Provider
public class HttpUnauthorizedExceptionHandler implements ExceptionMapper<HTTPException> {

    private static Logger logger = LoggerFactory.getLogger(HttpUnauthorizedExceptionHandler.class);

    @Override
    public Response toResponse(HTTPException exception) {
        logger.warn(exception.getMessage());
        logger.trace(exception.getMessage(), exception);
        return HttpResponse.getHttpFAIL(exception);
    }
}
