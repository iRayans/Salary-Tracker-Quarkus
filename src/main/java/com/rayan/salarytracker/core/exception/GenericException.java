package com.rayan.salarytracker.core.exception;

public class GenericException extends Exception{

    private String code;

    public GenericException() {
    }

    public GenericException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


