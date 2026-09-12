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

import com.example.community.dto.CommentCreateRequest;
import com.example.community.dto.CommentResponse;
import com.example.community.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(
            @PathVariable Long postId,
            @Valid @RequestBody CommentCreateRequest request,
            UriComponentsBuilder uriBuilder) {

        CommentResponse response = commentService.create(postId, request);

        URI location = uriBuilder
                .path("/posts/{postId}/comments/{commentId}")
                .buildAndExpand(postId, response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<CommentResponse>> findAll(
            @PathVariable Long postId) {

        List<CommentResponse> responses = commentService.findAll(postId);

        return ResponseEntity.ok(responses);
    }
    
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long postId,
            @PathVariable Long commentId) {

        commentService.delete(postId, commentId);

        return ResponseEntity.noContent().build();
    }
}