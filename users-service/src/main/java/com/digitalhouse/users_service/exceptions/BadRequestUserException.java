package com.digitalhouse.users_service.exceptions;

public class BadRequestUserException extends RuntimeException {
    public BadRequestUserException(String msg) {
        super(msg);
    }
}
