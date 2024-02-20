package com.example.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @SequenceGenerator(
            name = "COMMENT_ID_SEQ",
            sequenceName = "COMMENT_ID_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COMMENT_ID_SEQ"
    )
    private Long id;

    private String comment;

    private LocalDateTime time = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String comment) {
        this.comment = comment;
    }
}
