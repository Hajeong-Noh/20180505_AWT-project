package com.polimi.awt.payload.HttpResponseStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class OkResponse extends ApiResponse {

    public OkResponse() {
        super("Successful Operation");
    }

    public OkResponse(String message) {
        super(message);
    }
}
