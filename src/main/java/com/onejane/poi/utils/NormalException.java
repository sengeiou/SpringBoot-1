package com.onejane.poi.utils;

public class NormalException extends RuntimeException{
    private static final long serialVersionUID = 2771587260580067612L;

    private String error;

    public NormalException(String message) {
        super(message);
    }

    public NormalException(String message, String error) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}