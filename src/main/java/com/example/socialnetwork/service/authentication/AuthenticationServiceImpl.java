package com.example.socialnetwork.service.authentication;

import com.example.socialnetwork.dto.AuthenticationResponse;
import com.example.socialnetwork.dto.AuthorizationRequest;
import com.example.socialnetwork.dto.RegistrationRequest;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.*;
import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<AuthenticationResponse> register(RegistrationRequest request) {
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getRole(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );
        String token = jwtService.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse(
                TRUE,
                repository.save(user),
                token
        );

        return new ResponseEntity<>(response, CREATED);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> logIn(AuthorizationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.
                findByEmail(request.getEmail()).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = jwtService.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse(
                TRUE,
                user,
                token
        );

        return new ResponseEntity<>(response, ACCEPTED);
    }
}
