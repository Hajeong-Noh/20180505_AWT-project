package com.polimi.awt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super ("The resource you requested doesn't exist.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
