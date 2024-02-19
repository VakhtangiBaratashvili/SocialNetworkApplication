package com.example.socialnetwork.handler;

import com.example.socialnetwork.dto.ApiErrorResponse;
import com.example.socialnetwork.exception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static java.lang.Boolean.*;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ApiErrorResponseHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> userAlreadyExistsExceptionHandler(
            UserAlreadyExistsException e
    ) {
        ApiErrorResponse response = new ApiErrorResponse(
                FALSE, e.getMessage(), BAD_REQUEST, LocalDateTime.now());

        return new ResponseEntity<>(response, BAD_REQUEST);
    }
}
