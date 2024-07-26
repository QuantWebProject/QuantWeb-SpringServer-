package com.quantweb.springserver.domain.user.exception;

public class UserNotExistsException extends RuntimeException {

    public UserNotExistsException(String message) {
        super(message);
    }
}
