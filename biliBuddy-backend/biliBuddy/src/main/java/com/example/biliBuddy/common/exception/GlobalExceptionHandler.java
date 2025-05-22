package com.example.biliBuddy.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class) //Error handling for any generic and custom error.
    public ResponseEntity<?> handleRuntimeException(RuntimeException re, WebRequest request) {
        Map<String, String> error = new HashMap<>(); //putting the error in hash map
        error.put("message", re.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //exception handling for validation errors.
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException anve) {
        Map<String, String> errors = new HashMap<>();
        anve.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
            }
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe) {
        return new ResponseEntity<>(Map.of("message", rnfe.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class) //For fallbacks.
    public ResponseEntity<?> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "Internal server error:" + ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
