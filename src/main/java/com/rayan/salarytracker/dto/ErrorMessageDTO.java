package com.rayan.salarytracker.dto;


public class ErrorMessageDTO {
    private String code;
    private String message;

    public ErrorMessageDTO(String code) {
        this.code = code;
        message = "";
    }

    public ErrorMessageDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}