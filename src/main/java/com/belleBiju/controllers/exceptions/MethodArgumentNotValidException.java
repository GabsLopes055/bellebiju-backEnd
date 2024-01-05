package com.belleBiju.controllers.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MethodArgumentNotValidException {

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardError> handleValidationException(org.springframework.web.bind.MethodArgumentNotValidException ex, HttpServletRequest request){

        BindingResult result = ex.getBindingResult();

        List<FieldError> fieldErrorList = result.getFieldErrors();

        List<String> errorMessage = new ArrayList<>();

        for(FieldError fieldError : fieldErrorList){
            String erro = "Campo: " + fieldError.getField() + ": " + fieldError.getDefaultMessage();
            errorMessage.add(erro);
        }
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError(errorMessage);
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
