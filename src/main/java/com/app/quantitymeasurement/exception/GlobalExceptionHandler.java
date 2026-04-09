package com.app.quantitymeasurement.exception;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Map<String,String> handle(Exception ex) {
        return Map.of("error", ex.getMessage());
    }
}