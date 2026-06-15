package com.belleBiju.service.exceptions;

public class EstoqueInsuficiente extends RuntimeException {

    public EstoqueInsuficiente(String message) {
        super(message);
    }
}
