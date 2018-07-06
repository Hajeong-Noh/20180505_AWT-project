package com.polimi.awt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException() {
        super ("Username not found.");
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
