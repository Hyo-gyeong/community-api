package com.example.community.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.community.domain.Post;
import com.example.community.dto.PostCreateRequest;
import com.example.community.dto.PostResponse;
import com.example.community.exception.PostNotFoundException;
import com.example.community.repository.PostRepository;

@Service
@Transactional(readOnly = true)
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Transactional
	public PostResponse create(PostCreateRequest request) {

		Post post = new Post(
				request.author(),
				request.title(),
				request.content()
		);

		Post savedPost = postRepository.save(post);

		return toResponse(savedPost);
	}

	public List<PostResponse> findAll() {

		return postRepository.findAll()
				.stream()
				.map(this::toResponse)
				.toList();
	}

	public PostResponse findById(Long id) {

		Post post = postRepository.findById(id)
				.orElseThrow(() -> new PostNotFoundException(id));

		return toResponse(post);
	}

	@Transactional
	public void delete(Long id) {

		Post post = postRepository.findById(id)
				.orElseThrow(() -> new PostNotFoundException(id));

		postRepository.delete(post);
	}

	private PostResponse toResponse(Post post) {

		return new PostResponse(
				post.getId(),
				post.getAuthor(),
				post.getTitle(),
				post.getContent(),
				post.getCreatedAt()
		);
	}
}