package com.belleBiju.controllers.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class InternalServerError {

    ResponseEntity<StandardError> internalServerError(com.belleBiju.service.exceptions.InternalServerError e, HttpServletRequest request) {

        List<String> list = new ArrayList<>(Collections.singleton(e.getMessage()));

        StandardError error = new StandardError();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setError(list);
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
