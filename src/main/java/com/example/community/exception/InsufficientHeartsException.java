package com.example.community.exception;

public class InsufficientHeartsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InsufficientHeartsException(String userId) {
        super("Insufficient hearts. userId=" + userId);
    }
}