package com.belleBiju.controllers.exceptions;

import com.belleBiju.controllers.exceptions.exampleExceptions.MissingInformationsError;
import com.belleBiju.controllers.exceptions.exampleExceptions.StandardError;
import com.belleBiju.service.exceptions.MissingInformation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MissingInformationException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<MissingInformationsError> MissingInformation(MethodArgumentNotValidException bindException){

        List<String> errors = new ArrayList<>();

        bindException.getFieldErrors().forEach(ex -> errors.add(ex.getDefaultMessage()));

        MissingInformationsError error = new MissingInformationsError();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError(errors);
        error.setMessage("Erro de validação");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);


    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseBody
//    public ResponseEntity<MissingInformationsError> MissingInformation(ConstraintViolationException methodArgumentNotValidException){
//
//        BindingResult bindingResult = (BindingResult) methodArgumentNotValidException.getConstraintViolations();
//
//        List<FieldError> listErrors = bindingResult.getFieldErrors();
//
//        List<String> errors = new ArrayList<>();
//
//        listErrors.forEach(error -> errors.add(error.getDefaultMessage()));
//
//        MissingInformationsError error = new MissingInformationsError();
//
//        error.setTimestamp(Instant.now());
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setError(errors);
//        error.setMessage(methodArgumentNotValidException.getMessage());
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//
//    }

}
