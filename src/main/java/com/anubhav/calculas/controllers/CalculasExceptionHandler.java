package com.anubhav.calculas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CalculasExceptionHandler {

    @ExceptionHandler(value = InvalidExpressionException.class)
    public void handleInvalidExpressionException(HttpServletResponse response) throws IOException {
       response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
