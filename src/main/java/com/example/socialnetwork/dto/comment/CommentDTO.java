package com.example.socialnetwork.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CommentDTO {
    private String comment;
    private LocalDateTime time;
    private String user;
}
