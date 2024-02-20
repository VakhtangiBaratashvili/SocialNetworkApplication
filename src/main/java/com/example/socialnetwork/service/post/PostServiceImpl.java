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
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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
}
