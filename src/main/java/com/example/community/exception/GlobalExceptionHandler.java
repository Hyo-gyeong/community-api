package com.example.community.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handlePostNotFound(PostNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<String> handleCommentNotFound(CommentNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
    
    @ExceptionHandler(DuplicatePostLikeException.class)
    public ResponseEntity<String> handleDuplicatePostLike(DuplicatePostLikeException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }
    
    @ExceptionHandler(PostLikeNotFoundException.class)
    public ResponseEntity<String> handlePostLikeNotFound(PostLikeNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
    
    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<String> handleCandidateNotFound(CandidateNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}