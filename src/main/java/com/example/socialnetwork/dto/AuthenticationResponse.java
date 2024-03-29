package com.example.socialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private Boolean success;
    private Object data;
    private String token;
}
