package com.example.community.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.community.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findAllByPost_Id(Long postId);
	
	Optional<Comment> findByIdAndPost_Id(Long commentId, Long postId);
}