package com.anubhav.calculas.controllers;

public class ErrorResponse {

    private String message;
    private static final boolean error = true;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }
}
