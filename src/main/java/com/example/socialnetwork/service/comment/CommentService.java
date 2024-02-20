package com.example.socialnetwork.service.comment;

import com.example.socialnetwork.dto.ApiSuccessResponse;
import com.example.socialnetwork.entity.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<ApiSuccessResponse> addComment(Long postId, Comment comment);

}
