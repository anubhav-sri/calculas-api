package com.anubhav.calculas.controllers;

public class CalculasResponse {
    private boolean error;
    private Double result;

    public CalculasResponse() {
    }

    public CalculasResponse(boolean error, Double result) {

        this.error = error;
        this.result = result;
    }

    public boolean isError() {
        return error;
    }

    public Double getResult() {
        return result;
    }
}
