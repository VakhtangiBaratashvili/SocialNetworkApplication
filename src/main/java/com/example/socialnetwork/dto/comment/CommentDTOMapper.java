package com.example.socialnetwork.dto.comment;

import com.example.socialnetwork.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CommentDTOMapper implements Function<Comment, CommentDTO> {
    @Override
    public CommentDTO apply(Comment comment) {
        return new CommentDTO(
                comment.getComment(),
                comment.getTime(),
                comment.getUser().getFirstName() + " " + comment.getUser().getLastName());
    }
}
