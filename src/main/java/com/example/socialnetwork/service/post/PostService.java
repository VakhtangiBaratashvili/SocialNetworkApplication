package com.example.socialnetwork.service.post;

import com.example.socialnetwork.dto.ApiSuccessResponse;
import com.example.socialnetwork.entity.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {
    ResponseEntity<ApiSuccessResponse> addPost(Post post);
    ResponseEntity<ApiSuccessResponse> getAllPosts();
    ResponseEntity<ApiSuccessResponse> getPostById(Long id);
    ResponseEntity<ApiSuccessResponse> getAllPostsByUser(Long id);
    ResponseEntity<ApiSuccessResponse> updatePostById(Long id, Post post);
    ResponseEntity<ApiSuccessResponse> deletePostById(Long id);
}
