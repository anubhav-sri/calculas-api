package com.anubhav.calculas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CalculasExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = InvalidExpressionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidExpressionException(InvalidExpressionException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
