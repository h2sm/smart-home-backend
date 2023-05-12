package com.h2sm.smarthomebackend.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {MessageDeliveryException.class})
    protected ResponseEntity<Object> handleOfflineHub(final RuntimeException ex, final WebRequest request) {
        log.debug("Wasnt able to send a message to hub");
        return handleExceptionInternal(ex, "Hub is not connected", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    protected ResponseEntity<Object> handleDeletingHub(final RuntimeException ex, final WebRequest request) {
        log.debug("Wasnt able to delete a hub");
        return handleExceptionInternal(ex, "Cannot delete hub with devices connected", new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    protected ResponseEntity<Object> handleUnsuccessfulLogin(final RuntimeException ex, final WebRequest request) {
        log.debug("cannot login ;(");
        return handleExceptionInternal(ex, "Login unsuccessful. Check your credentials.", new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

}
