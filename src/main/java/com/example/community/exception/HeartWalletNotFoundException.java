package com.example.community.exception;

public class HeartWalletNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HeartWalletNotFoundException(String userId) {
        super("Heart wallet not found. userId=" + userId);
    }
}