package com.artcode.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		log.warn("Invalid request: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}