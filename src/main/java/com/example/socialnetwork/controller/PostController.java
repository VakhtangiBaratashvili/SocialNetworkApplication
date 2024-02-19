package com.example.socialnetwork.controller;

import com.example.socialnetwork.dto.ApiSuccessResponse;
import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.service.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final PostService service;

    @PostMapping("/add")
    public ResponseEntity<ApiSuccessResponse> addPost(@RequestBody Post post) {
        return service.addPost(post);
    }

    @GetMapping
    public ResponseEntity<ApiSuccessResponse> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse> getPostById(@PathVariable Long id) {
        return service.getPostById(id);
    }
}
