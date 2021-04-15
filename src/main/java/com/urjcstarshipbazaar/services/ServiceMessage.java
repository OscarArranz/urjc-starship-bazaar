package com.urjcstarshipbazaar.services;

public class ServiceMessage {

    private int code;
    private String message;

    public ServiceMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceMessage(int code) {
        this.code = code;
        this.message = "";
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
