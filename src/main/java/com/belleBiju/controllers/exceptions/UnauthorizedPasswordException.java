package com.belleBiju.controllers.exceptions;

import com.belleBiju.service.exceptions.UnauthorizedPassword;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.Instant;

@ControllerAdvice
public class UnauthorizedPasswordException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnauthorizedPassword.class)
    public ResponseEntity<StandardError> passwordException(UnauthorizedPassword e, HttpServletRequest request){

        StandardError error = new StandardError();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setError(e.getMessage());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

}

