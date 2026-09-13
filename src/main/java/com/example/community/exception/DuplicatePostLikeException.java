package com.example.community.exception;

public class DuplicatePostLikeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicatePostLikeException(Long postId, String userId) {
        super("Post like already exists. postId=" + postId + ", userId=" + userId);
    }
}