package com.example.socialnetwork.dto;

import com.example.socialnetwork.enums.Gender;
import com.example.socialnetwork.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Role role;
    private String email;
    private String password;
}
