package com.nine.intface.common.exception;

public class NullParameterException extends Exception {
    private static final long serialVersionUID = 1L;

    private static String message;


    public NullParameterException(String message) {
        super(message);
    }

    public NullParameterException() {
        super(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
