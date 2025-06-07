package com.recruitment.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    String errorMessage = ex.getBindingResult().getFieldErrors().stream()
	        .map(error -> error.getField() + ": " + error.getDefaultMessage())
	        .findFirst()
	        .orElse("Validation error");

	    return ResponseEntity.badRequest().body(errorMessage);
	}

}
