package com.example.socialnetwork.service.post;

import com.example.socialnetwork.dto.ApiSuccessResponse;
import com.example.socialnetwork.dto.post.PostDTO;
import com.example.socialnetwork.dto.post.PostDTOMapper;
import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.exception.PostNotFoundException;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.*;
import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final UserRepository userRepository;
    private final PostDTOMapper dtoMapper;

    @Override
    public ResponseEntity<ApiSuccessResponse> addPost(Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        user.addPost(post);
        userRepository.save(user);
        repository.save(post);
        ApiSuccessResponse response = new ApiSuccessResponse(
                TRUE,
                dtoMapper.apply(post)
        );
        return new ResponseEntity<>(response, CREATED);
    }

    @Override
    public ResponseEntity<ApiSuccessResponse> getAllPosts() {
        List<PostDTO> posts = repository.findAll().stream().map(dtoMapper).toList();
        ApiSuccessResponse response = new ApiSuccessResponse(
                TRUE,
                posts
        );
        return new ResponseEntity<>(response, OK);
    }

    @Override
    public ResponseEntity<ApiSuccessResponse> getPostById(Long id) {
        Post post = repository.
                findById(id).
                orElseThrow(() -> new PostNotFoundException("Post not found"));

        ApiSuccessResponse response = new ApiSuccessResponse(
                TRUE,
                dtoMapper.apply(post)
        );
        return new ResponseEntity<>(response, OK);
    }

    @Override
    public ResponseEntity<ApiSuccessResponse> getAllPostsByUser(Long id) {
        User user = userRepository.
                findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<PostDTO> postsByUser = repository.findAllByUser(user).stream().map(dtoMapper).toList();

        ApiSuccessResponse response = new ApiSuccessResponse(
                TRUE,
                postsByUser
        );
        return new ResponseEntity<>(response, OK);
    }

    @Override
    public ResponseEntity<ApiSuccessResponse> updatePostById(Long id, Post post) {

        Post getPost = repository.
                findById(id).
                orElseThrow(() -> new PostNotFoundException("Post not found"));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getPosts().contains(getPost)) {
            throw new RuntimeException("You don't own this post");
        }
        if (post != null && post.getSummary() != null && !post.getSummary().isBlank()) {
            getPost.setSummary(post.getSummary());
            getPost.setTime(post.getTime());
            repository.save(getPost);
        }

        ApiSuccessResponse response = new ApiSuccessResponse(
                TRUE,
                dtoMapper.apply(getPost)
        );
        return new ResponseEntity<>(response, ACCEPTED);
    }

    @Override
    public ResponseEntity<ApiSuccessResponse> deletePostById(Long id) {
        Post post = repository.
                findById(id).
                orElseThrow(() -> new PostNotFoundException("Post not found"));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getPosts().contains(post)) {
            throw new RuntimeException("You don't own this post");
        }
        repository.deleteById(id);
        user.removePost(post);
        userRepository.save(user);

        ApiSuccessResponse response = new ApiSuccessResponse(
                TRUE,
                dtoMapper.apply(post)
        );
        return new ResponseEntity<>(response, ACCEPTED);
    }
}
