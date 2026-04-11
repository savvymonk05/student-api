package com.himanshu.student_api;

import com.himanshu.student_api.model.Role;
import com.himanshu.student_api.model.Users;
import com.himanshu.student_api.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository repo, PasswordEncoder encoder) {
        return args -> {

            System.out.println("🔥 Checking admin user...");

            // ✅ CHECK FIRST
            if (repo.findByUsername("admin").isEmpty()) {

                Users user = new Users();
                user.setUsername("admin");
                user.setPassword(encoder.encode("admin123")); // 🔐 IMPORTANT
                user.setRole(Role.ADMIN);

                repo.save(user);

                System.out.println("🔥 Admin user created");

            } else {
                System.out.println("✅ Admin already exists");
            }
        };
    }
}