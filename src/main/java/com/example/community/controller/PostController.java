package com.example.community.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.community.dto.PostCreateRequest;
import com.example.community.dto.PostResponse;
import com.example.community.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping
	public ResponseEntity<PostResponse> create(
			@Valid @RequestBody PostCreateRequest request,
			UriComponentsBuilder uriBuilder) {

		PostResponse response = postService.create(request);

		URI location = uriBuilder
				.path("/posts/{id}")
				.buildAndExpand(response.id())
				.toUri();

		return ResponseEntity
				.created(location)
				.body(response);
	}

	@GetMapping
	public ResponseEntity<List<PostResponse>> findAll() {

		List<PostResponse> responses = postService.findAll();

		return ResponseEntity.ok(responses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> findById(
			@PathVariable Long id) {

		PostResponse response = postService.findById(id);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@PathVariable Long id) {

		postService.delete(id);

		return ResponseEntity.noContent().build();
	}
}