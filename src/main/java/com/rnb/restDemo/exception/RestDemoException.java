package com.rnb.restDemo.exception;

public class RestDemoException extends Exception{

    public RestDemoException(String message) {
        super(message);
    }

    public RestDemoException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestDemoException(Throwable cause) {
        super(cause);
    }
}
