package com.anubhav.calculas.controllers;

public class ErrorResponse {

    private String message;
    private final boolean error = false;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }
}
