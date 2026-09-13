package com.example.community.exception;

public class DuplicateHeartWalletException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateHeartWalletException(String userId) {
        super("Heart wallet already exists. userId=" + userId);
    }
}