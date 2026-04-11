package com.himanshu.student_api.controller;

import com.himanshu.student_api.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                return "Login Successful";
            } else {
                return "Authentication Failed";
            }

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 THIS IS THE KEY
            return "Error: " + e.getMessage();
        }
    }
}