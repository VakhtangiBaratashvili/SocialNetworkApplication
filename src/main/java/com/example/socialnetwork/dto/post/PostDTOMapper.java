package com.example.socialnetwork.dto.post;

import com.example.socialnetwork.entity.Post;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PostDTOMapper implements Function<Post, PostDTO> {
    @Override
    public PostDTO apply(Post post) {
        return new PostDTO(
                post.getId(),
                post.getSummary(),
                post.getLikes(),
                post.getTime(),
                post.getComments(),
                post.getUser().getFirstName() + " " + post.getUser().getLastName()
        );
    }
}
