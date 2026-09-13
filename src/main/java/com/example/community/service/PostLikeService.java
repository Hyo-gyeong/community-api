package com.example.community.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.community.domain.Post;
import com.example.community.domain.PostLike;
import com.example.community.dto.PostLikeCountResponse;
import com.example.community.dto.PostLikeCreateRequest;
import com.example.community.dto.PostLikeResponse;
import com.example.community.exception.DuplicatePostLikeException;
import com.example.community.exception.PostLikeNotFoundException;
import com.example.community.exception.PostNotFoundException;
import com.example.community.repository.PostLikeRepository;
import com.example.community.repository.PostRepository;

@Service
@Transactional(readOnly = true)
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public PostLikeService(
            PostLikeRepository postLikeRepository,
            PostRepository postRepository) {
        this.postLikeRepository = postLikeRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public PostLikeResponse create(
            Long postId,
            PostLikeCreateRequest request) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        if (postLikeRepository.existsByPost_IdAndUserId(
                postId,
                request.userId())) {
            throw new DuplicatePostLikeException(
                    postId,
                    request.userId()
            );
        }

        PostLike postLike = new PostLike(
                post,
                request.userId()
        );

        PostLike savedPostLike = postLikeRepository.save(postLike);

        return toResponse(savedPostLike);
    }

    @Transactional
    public void delete(Long postId, String userId) {

        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException(postId);
        }

        PostLike postLike = postLikeRepository
                .findByPost_IdAndUserId(postId, userId)
                .orElseThrow(
                        () -> new PostLikeNotFoundException(postId, userId)
                );

        postLikeRepository.delete(postLike);
    }

    public PostLikeCountResponse count(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException(postId);
        }

        long count = postLikeRepository.countByPost_Id(postId);

        return new PostLikeCountResponse(postId, count);
    }

    private PostLikeResponse toResponse(PostLike postLike) {
        return new PostLikeResponse(
                postLike.getId(),
                postLike.getPost().getId(),
                postLike.getUserId(),
                postLike.getCreatedAt()
        );
    }
}