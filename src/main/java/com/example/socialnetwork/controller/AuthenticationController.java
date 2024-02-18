package com.example.socialnetwork.controller;

import com.example.socialnetwork.dto.AuthenticationResponse;
import com.example.socialnetwork.dto.AuthorizationRequest;
import com.example.socialnetwork.dto.RegistrationRequest;
import com.example.socialnetwork.service.authentication.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> logIn(@RequestBody AuthorizationRequest request) {
        return service.logIn(request);
    }
}
