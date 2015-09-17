package com.restapi.exceptions;

import javax.xml.ws.http.HTTPException;

public class UnauthorizedException extends HTTPException {

    private final String message;

    public UnauthorizedException(String message) {
        super(401);
        this.message = message;
    }

    public UnauthorizedException() {
        super(401);
        this.message = "UnauthorizedException";
    }

    public String getMessage() {
        return message;
    }
}
