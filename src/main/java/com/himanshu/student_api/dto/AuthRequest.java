package com.himanshu.student_api.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;

    // getters & setters
}