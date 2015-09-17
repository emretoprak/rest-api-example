package com.restapi.exceptions;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

public class ValidationException extends WebApplicationException {

    private final List<String> messages;

    public ValidationException(List<String> messages) {
        super(400);
        this.messages = messages;
    }

    public ValidationException() {
        super(400);
        this.messages = new ArrayList<>();
    }

    public List<String> getMessages() {
        return messages;
    }
}
