package com.example.socialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationResponse {
    Boolean success;
    Object data;
    String token;
}
