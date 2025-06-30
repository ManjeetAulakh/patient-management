package com.pm.patientservice.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(
        GlobalExceptionHandler.class);
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> jpaValidationException(MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
            error -> errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> emailAlreadyExists(EmailAlreadyExistsException ex) {

        log.warn("Email is already exists {}" , ex.getMessage());
        
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Email already exists. Please use a different email");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String>> patientNotFound(PatientNotFoundException ex) {

        log.warn("Patient Not found {}" , ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Patient not found ");
        return ResponseEntity.badRequest().body(errors);
    }

}
