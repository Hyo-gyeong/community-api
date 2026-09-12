package com.example.community.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<String> handlePostNotFound(
			PostNotFoundException e) {

		return ResponseEntity
				.status(404)
				.body(e.getMessage());
	}
}