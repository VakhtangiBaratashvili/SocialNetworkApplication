package com.example.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @SequenceGenerator(
            name = "POST_ID_SEQ",
            sequenceName = "POST_ID_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "POST_ID_SEQ"
    )
    private Long id;

    private String summary;

    private Integer likes = 0;

    private LocalDateTime time = LocalDateTime.now();

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(String summary) {
        this.summary = summary;
    }

    public void addComment(Comment comment) {
        if (comment != null && !this.comments.contains(comment)) {
            this.comments.add(comment);
        }
    }
}
