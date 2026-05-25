package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.entity.User;
import com.travel.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuditLogService auditLogService;

    // LOGIN
    public User login(
            String email,
            String password
    ) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email")
                );

        if (!passwordEncoder.matches(
                password,
                user.getPassword()
        )) {

            auditLogService.saveLog(
                    "LOGIN_FAILED",
                    email,
                    "FAILED"
            );

            throw new RuntimeException(
                    "Invalid Password"
            );
        }

        auditLogService.saveLog(
                "LOGIN_SUCCESS",
                email,
                "SUCCESS"
        );

        return user;
    }
}