package com.example.community.exception;

public class PostLikeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PostLikeNotFoundException(Long postId, String userId) {
        super("Post like not found. postId=" + postId + ", userId=" + userId);
    }
}