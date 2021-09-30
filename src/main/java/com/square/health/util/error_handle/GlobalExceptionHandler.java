package com.square.health.util.error_handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:13 AM
 **/

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public GlobalExceptionHandler() {
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception
    ) {
        ErrorDetails globalResponse = new ErrorDetails(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(globalResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleIdNotFoundException(BadRequestException badRequestException) {
        ErrorDetails globalResponse = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                badRequestException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(globalResponse, HttpStatus.BAD_REQUEST);
    }
}
