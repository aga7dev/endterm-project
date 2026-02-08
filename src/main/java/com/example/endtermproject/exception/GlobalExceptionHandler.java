package com.example.endtermproject.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        ApiError err = new ApiError(LocalDateTime.now(), 404, "NOT_FOUND", ex.getMessage(), req.getRequestURI());
        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex, HttpServletRequest req) {
        ApiError err = new ApiError(LocalDateTime.now(), 400, "BAD_REQUEST", ex.getMessage(), req.getRequestURI());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String msg = ex.getBindingResult().getFieldErrors().isEmpty()
                ? "Validation error"
                : ex.getBindingResult().getFieldErrors().get(0).getField() + ": " +
                ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        ApiError err = new ApiError(LocalDateTime.now(), 400, "VALIDATION_ERROR", msg, req.getRequestURI());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAny(Exception ex, HttpServletRequest req) {
        ApiError err = new ApiError(LocalDateTime.now(), 500, "INTERNAL_SERVER_ERROR", ex.getMessage(), req.getRequestURI());
        return ResponseEntity.status(500).body(err);
    }
}
