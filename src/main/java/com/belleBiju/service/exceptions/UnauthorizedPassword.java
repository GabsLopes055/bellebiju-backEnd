package com.belleBiju.service.exceptions;

public class UnauthorizedPassword extends RuntimeException {

    public UnauthorizedPassword(String message) {
        super(message);
    }
}
