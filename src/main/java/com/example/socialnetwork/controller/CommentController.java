package com.example.socialnetwork.controller;

import com.example.socialnetwork.dto.ApiSuccessResponse;
import com.example.socialnetwork.entity.Comment;
import com.example.socialnetwork.service.comment.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping("/{postId}/add")
    public ResponseEntity<ApiSuccessResponse> addComment(
            @PathVariable Long postId, @RequestBody Comment comment
            ) {
        return service.addComment(postId, comment);
    }
}
