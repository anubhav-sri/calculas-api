package com.anubhav.calculas.controllers;

public class InvalidExpressionException extends RuntimeException {
    public InvalidExpressionException(String message) {
        super(message);
    }
}
