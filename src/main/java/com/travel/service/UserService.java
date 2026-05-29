package com.travel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.dto.CreateUserRequest;
import com.travel.dto.LoginRequest;
import com.travel.dto.LoginResponse;
import com.travel.dto.UserResponse;
import com.travel.entity.User;
import com.travel.repository.UserRepository;
import com.travel.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ================= CREATE USER =================
    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setDepartment(request.getDepartment());
        user.setActive(true);

        userRepository.save(user);

        return mapToDTO(user);
    }

    // ================= LOGIN =================
    public LoginResponse login(LoginRequest request) {

        String email = request.getEmail() != null
                ? request.getEmail().trim()
                : null;

        String password = request.getPassword() != null
                ? request.getPassword().trim()
                : null;

        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        boolean passwordMatches = passwordEncoder.matches(
                password,
                user.getPassword()
        );

        if (!passwordMatches && password != null) {
            passwordMatches = password.equals(user.getPassword());

            if (passwordMatches) {
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
            }
        }

        if (!passwordMatches) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = JwtUtil.generateToken(
        	    user.getEmail(),
        	    user.getRole().name()  // 🔥 ADD ROLE HERE
        	);

        return new LoginResponse(
                token,
                user.getRole().name(),
                user.getEmail(),
                user.getName()
        );
    }

    // ================= GET ALL USERS =================
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ================= GET USER BY ID =================
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToDTO(user);
    }

    // ================= UPDATE USER =================
    public UserResponse updateUser(Long id, CreateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        user.setRole(request.getRole());
        user.setDepartment(request.getDepartment());

        userRepository.save(user);

        return mapToDTO(user);
    }

    // ================= DELETE USER =================
    public String deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);

        return "User deleted successfully";
    }

    // ================= DISABLE USER =================
    public String disableUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(false);
        userRepository.save(user);

        return "User disabled successfully";
    }

    // ================= DTO MAPPER =================
    private UserResponse mapToDTO(User user) {

        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole() != null ? user.getRole().name() : null);
        dto.setDepartment(user.getDepartment());
        dto.setActive(user.isActive());

        return dto;
    }
}
