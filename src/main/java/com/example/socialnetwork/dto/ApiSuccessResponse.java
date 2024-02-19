package com.example.socialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiSuccessResponse {
    private Boolean success;
    private Object data;
}
