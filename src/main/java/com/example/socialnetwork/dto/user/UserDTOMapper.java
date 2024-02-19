package com.example.socialnetwork.dto.user;

import com.example.socialnetwork.dto.post.PostDTO;
import com.example.socialnetwork.dto.post.PostDTOMapper;
import com.example.socialnetwork.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserDTOMapper implements Function<User, UserDTO> {

    private final PostDTOMapper dtoMapper;

    @Override
    public UserDTO apply(User user) {
        List<PostDTO> postDTOS = user.getPosts().stream().map(dtoMapper).toList();
        return new UserDTO(user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                postDTOS);
    }
}
