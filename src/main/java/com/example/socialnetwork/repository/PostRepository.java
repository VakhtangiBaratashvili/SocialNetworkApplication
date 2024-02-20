package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);
}
