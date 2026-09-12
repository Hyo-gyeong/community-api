package com.example.community.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.community.domain.Comment;
import com.example.community.domain.Post;
import com.example.community.dto.CommentCreateRequest;
import com.example.community.dto.CommentResponse;
import com.example.community.exception.CommentNotFoundException;
import com.example.community.exception.PostNotFoundException;
import com.example.community.repository.CommentRepository;
import com.example.community.repository.PostRepository;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public CommentResponse create(Long postId, CommentCreateRequest request) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        Comment comment = new Comment(
                post,
                request.author(),
                request.content()
        );

        Comment savedComment = commentRepository.save(comment);

        return toResponse(savedComment);
    }
    
    public List<CommentResponse> findAll(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException(postId);
        }

        return commentRepository.findAllByPost_Id(postId)
                .stream()
                .map(this::toResponse)
                .toList();
    }
    
    private CommentResponse toResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getPost().getId(),
                comment.getAuthor(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
    
    @Transactional
    public void delete(Long postId, Long commentId) {

        Comment comment = commentRepository
                .findByIdAndPost_Id(commentId, postId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        commentRepository.delete(comment);
    }
}