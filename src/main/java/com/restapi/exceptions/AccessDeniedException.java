package com.restapi.exceptions;


import javax.xml.ws.http.HTTPException;

public class AccessDeniedException extends HTTPException {

    private final String message;

    public AccessDeniedException(String message) {
        super(403);
        this.message = message;
    }

    public AccessDeniedException() {
        super(403);
        this.message = "Erişim Engellendi";
    }

    public String getMessage() {
        return message;
    }
}
