package com.example.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.community.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}