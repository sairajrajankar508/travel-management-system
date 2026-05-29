package com.travel.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.dto.CreateUserRequest;
import com.travel.dto.UserResponse;
import com.travel.entity.TravelPolicy;
import com.travel.entity.User;
import com.travel.service.AdminService;
import com.travel.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;

    // ================= USER MANAGEMENT =================

    @PostMapping("/create-user")
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        return adminService.createUser(request);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return adminService.getUsers();
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody CreateUserRequest request
    ) {
        return adminService.updateUser(id, request);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return adminService.deleteUser(id);
    }

    @PutMapping("/users/{id}/toggle-status")
    public ResponseEntity<String> toggleUserStatus(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(!user.isActive());

        userRepository.save(user);

        return ResponseEntity.ok("User status updated");
    }

    // ================= POLICY MANAGEMENT =================

    @PostMapping("/policy")
    public String createPolicy(@RequestBody TravelPolicy policy) {
        return adminService.setPolicy(
                policy.getMaxBudget(),
                policy.getAllowedClass()
        );
    }

    @GetMapping("/policy")
    public List<TravelPolicy> getPolicies() {
        return adminService.getAllPolicies();
    }

    @PutMapping("/policy/{id}")
    public TravelPolicy updatePolicy(
            @PathVariable Long id,
            @RequestBody TravelPolicy policy
    ) {
        return adminService.updatePolicy(id, policy.getMaxBudget(), policy.getAllowedClass());
    }

    @PutMapping("/policy/toggle/{id}")
    public String togglePolicy(@PathVariable Long id) {
        return adminService.togglePolicy(id);
    }

    @DeleteMapping("/policy/{id}")
    public String deletePolicy(@PathVariable Long id) {
        return adminService.deletePolicy(id);
    }

    // ================= REPORTS =================

    @GetMapping("/reports")
    public Map<String, Object> getReports() {
        return adminService.getAdminReport();
    }

} 