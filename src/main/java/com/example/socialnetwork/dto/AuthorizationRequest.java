package com.example.socialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthorizationRequest {
    private String email;
    private String password;
}
