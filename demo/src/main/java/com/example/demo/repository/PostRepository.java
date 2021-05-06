package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.board.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}