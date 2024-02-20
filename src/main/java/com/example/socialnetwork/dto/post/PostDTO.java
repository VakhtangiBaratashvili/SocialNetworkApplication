package com.example.socialnetwork.dto.post;

import com.example.socialnetwork.dto.comment.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class PostDTO {
    private Long id;
    private String summary;
    private Integer likes;
    private LocalDateTime time;
    private List<CommentDTO> comments;
    private String user;
}
