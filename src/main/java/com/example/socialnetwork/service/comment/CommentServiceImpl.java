package com.example.socialnetwork.service.comment;

import com.example.socialnetwork.dto.ApiSuccessResponse;
import com.example.socialnetwork.dto.comment.CommentDTOMapper;
import com.example.socialnetwork.entity.Comment;
import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.exception.PostNotFoundException;
import com.example.socialnetwork.repository.CommentRepository;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.CREATED;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentDTOMapper dtoMapper;

    @Override
    public ResponseEntity<ApiSuccessResponse> addComment(Long postId, Comment comment) {
        Post post = postRepository.
                findById(postId).
                orElseThrow(() -> new PostNotFoundException("Post not found"));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        comment.setPost(post);
        comment.setUser(user);
        post.addComment(comment);
        user.addComment(comment);
        postRepository.save(post);
        userRepository.save(user);
        commentRepository.save(comment);

        ApiSuccessResponse response = new ApiSuccessResponse(
                TRUE,
                dtoMapper.apply(comment)
        );
        return new ResponseEntity<>(response, CREATED);
    }
}
