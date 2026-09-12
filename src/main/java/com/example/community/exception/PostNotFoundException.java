package com.example.community.exception;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PostNotFoundException(Long id) {
		super("Post not found. id=" + id);
	}
}