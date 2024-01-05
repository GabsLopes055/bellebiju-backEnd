package com.belleBiju.service.exceptions;

public class InternalServerError extends RuntimeException{

    public InternalServerError(String message) {
        super(message);
    }

}
