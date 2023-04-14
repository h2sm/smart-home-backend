package com.h2sm.smarthomebackend.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {MessageDeliveryException.class})
    protected ResponseEntity<Object> handleOfflineHub(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, "Hub is not connected", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
