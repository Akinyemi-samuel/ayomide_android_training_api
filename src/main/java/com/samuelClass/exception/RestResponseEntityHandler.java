package com.samuelClass.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleUserExists(ApiException apiException){
        ApiException apiException1 = new ApiException(apiException.getMessage(), apiException.getHttpStatus());
        return new ResponseEntity<>(apiException1.getMessage(), apiException1.getHttpStatus());
    }
}
