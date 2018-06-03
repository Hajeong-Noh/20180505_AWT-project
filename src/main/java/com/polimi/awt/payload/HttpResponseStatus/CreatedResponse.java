package com.polimi.awt.payload.HttpResponseStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CREATED)
public class CreatedResponse extends ApiResponse {

    public CreatedResponse() {
        super("Resource created successfully");
    }

    public CreatedResponse(String message) {
        super(message);
    }
}
