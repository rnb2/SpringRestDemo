package com.rnb.restDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestDemoExceptionHandler {

    @ExceptionHandler(RestDemoException.class)
    public ResponseEntity<MessageResponse> handleException(RestDemoException exc) {
        MessageResponse errorResponse = new MessageResponse(exc.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MessageResponse> handleException(Exception exc) {
        MessageResponse errorResponse = new MessageResponse(exc.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
