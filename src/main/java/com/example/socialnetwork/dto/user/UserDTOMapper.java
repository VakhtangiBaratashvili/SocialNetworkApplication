package com.example.socialnetwork.dto.user;

import com.example.socialnetwork.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getPosts());
    }
}
