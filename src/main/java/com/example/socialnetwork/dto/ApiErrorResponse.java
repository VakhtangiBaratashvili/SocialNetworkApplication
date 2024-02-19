package com.example.socialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ApiErrorResponse {
    private Boolean success;
    private String message;
    private HttpStatus status;
    private LocalDateTime timeStamp;
}
