package com.himanshu.student_api.controller;

import com.himanshu.student_api.dto.AuthRequest;
import com.himanshu.student_api.security.JwtUtil;
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
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        System.out.println("🔥 LOGIN API HIT");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            System.out.println("✅ AUTH SUCCESS");

            return jwtUtil.generateToken(request.getUsername());

        } catch (Exception e) {
            System.out.println("❌ AUTH FAILED");
            e.printStackTrace(); // 🔥 THIS WILL SHOW REAL REASON
            throw e;
        }
    }
}