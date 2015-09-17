package com.restapi.responser;

import com.restapi.exceptions.ValidationException;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.http.HTTPException;
import java.util.List;

public class HttpResponse {

    public static Response getOK() {
        JSONObject response = new JSONObject();
        response.put("status", 200);
        response.put("result", "OK");
        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE).entity(response.toString()).build();
    }

    public static Response getHttpFAIL(HTTPException exception) {
        JSONObject response = new JSONObject();
        String message = exception.getMessage();
        if (message != null && message.contains(":")) {
            message = message.split(":")[1];
        }
        response.put("status", exception.getStatusCode());
        response.put("message", message);
        response.put("result", "FAIL");

        return Response.status(exception.getStatusCode()).entity(response.toString()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public static Response getHttpFAIL(int statusCode, String message) {
        JSONObject response = new JSONObject();
        response.put("status", statusCode);
        response.put("message", message);
        response.put("result", "FAIL");

        return Response.status(statusCode).entity(response.toString()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public static Response getRuntimeFAIL(Exception exception) {
        JSONObject response = new JSONObject();
        String message = exception.getMessage();
        if (message != null && message.contains(":")) {
            message = message.split(":")[1];
        }
        response.put("status", 500);
        response.put("message", message);
        response.put("result", "FAIL");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response.toString()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public static Response getCreatedOK() {
        JSONObject response = new JSONObject();
        response.put("status", 201);
        response.put("result", "CREATED");
        return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON_TYPE).entity(response.toString()).build();
    }

    public static Response getValidationFAIL(ValidationException e) {
        JSONObject response = new JSONObject();
        String message = "Validation error:";
        List<String> messages = e.getMessages();
        for (String s : messages) {
            message += s + "\r\n";
        }
        response.put("status", 400);
        response.put("message", message);
        return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON_TYPE).entity(response.toString()).build();
    }
}
