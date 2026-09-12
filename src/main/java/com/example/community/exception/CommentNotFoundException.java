package com.example.community.exception;

public class CommentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CommentNotFoundException(Long commentId) {
        super("Comment not found. id=" + commentId);
    }
}