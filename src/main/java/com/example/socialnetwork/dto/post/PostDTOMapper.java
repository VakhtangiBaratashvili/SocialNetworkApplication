package com.example.socialnetwork.dto.post;

import com.example.socialnetwork.dto.comment.CommentDTO;
import com.example.socialnetwork.dto.comment.CommentDTOMapper;
import com.example.socialnetwork.entity.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class PostDTOMapper implements Function<Post, PostDTO> {

    private final CommentDTOMapper dtoMapper;

    @Override
    public PostDTO apply(Post post) {

        List<CommentDTO> comments = post.getComments().stream().map(dtoMapper).toList();

        return new PostDTO(
                post.getId(),
                post.getSummary(),
                post.getLikes(),
                post.getTime(),
                comments,
                post.getUser().getFirstName() + " " + post.getUser().getLastName()
        );
    }
}
