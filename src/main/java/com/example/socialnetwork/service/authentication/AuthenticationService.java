package com.example.socialnetwork.service.authentication;

import com.example.socialnetwork.dto.AuthenticationResponse;
import com.example.socialnetwork.dto.AuthorizationRequest;
import com.example.socialnetwork.dto.RegistrationRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<AuthenticationResponse> register(RegistrationRequest request);
    ResponseEntity<AuthenticationResponse> logIn(AuthorizationRequest request);
}
