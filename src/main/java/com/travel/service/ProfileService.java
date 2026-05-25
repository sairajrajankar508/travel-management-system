//package com.travel.service;
//
//import com.travel.entity.User;
//import com.travel.repository.UserRepository;
//
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class ProfileService {
//
//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    // GET LOGGED-IN USER PROFILE
//    public User getProfile() {
//
//        Authentication auth =
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication();
//
//        String email =
//                auth.getName();
//
//        return userRepository
//                .findByEmail(email)
//                .orElseThrow();
//    }
//
//    // UPDATE PROFILE
//    public User updateProfile(
//            User updatedUser
//    ) {
//
//        Authentication auth =
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication();
//
//        String email =
//                auth.getName();
//
//        User existingUser =
//                userRepository
//                        .findByEmail(email)
//                        .orElseThrow();
//
//        // UPDATE NAME
//        existingUser.setName(
//                updatedUser.getName()
//        );
//
//        
//        // UPDATE PASSWORD IF PROVIDED
//        if (updatedUser.getPassword() != null
//                && !updatedUser.getPassword().isEmpty()) {
//
//            existingUser.setPassword(
//
//                    passwordEncoder.encode(
//                            updatedUser.getPassword()
//                    )
//            );
//        }
//
//        return userRepository.save(
//                existingUser
//        );
//    }
//}