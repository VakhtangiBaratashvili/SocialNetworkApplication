package com.example.socialnetwork.dto.user;

import com.example.socialnetwork.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private List<Post> posts;
}
