package com.example.community.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.community.dto.PostLikeCountResponse;
import com.example.community.dto.PostLikeCreateRequest;
import com.example.community.dto.PostLikeResponse;
import com.example.community.service.PostLikeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts/{postId}/likes")
public class PostLikeController {

    private final PostLikeService postLikeService;

    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping
    public ResponseEntity<PostLikeResponse> create(
            @PathVariable Long postId,
            @Valid @RequestBody PostLikeCreateRequest request,
            UriComponentsBuilder uriBuilder) {

        PostLikeResponse response =
                postLikeService.create(postId, request);

        URI location = uriBuilder
                .path("/posts/{postId}/likes/{likeId}")
                .buildAndExpand(postId, response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @PathVariable Long postId,
            @RequestParam String userId) {

        postLikeService.delete(postId, userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<PostLikeCountResponse> count(
            @PathVariable Long postId) {

        PostLikeCountResponse response =
                postLikeService.count(postId);

        return ResponseEntity.ok(response);
    }
}