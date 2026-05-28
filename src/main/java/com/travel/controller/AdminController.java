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
import com.travel.entity.WorkflowConfig;
import com.travel.service.AdminService;
import com.travel.service.EmployeeService;
import com.travel.service.NotificationService;
import com.travel.repository.UserRepository;
import com.travel.repository.WorkflowConfigRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private WorkflowConfigRepository workflowConfigRepository;

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

    // ================= WORKFLOW CONFIGURATION =================

    @GetMapping("/workflow")
    public List<WorkflowConfig> getWorkflowConfig() {
        List<WorkflowConfig> configs = workflowConfigRepository.findAll();
        if (configs.isEmpty()) {
            List<WorkflowConfig> defaults = List.of(
                    createConfig("requireManagerApproval", "true"),
                    createConfig("requireFinanceApproval", "true"),
                    createConfig("autoApproveThreshold", "5000"),
                    createConfig("maxDaysPending", "7")
            );
            workflowConfigRepository.saveAll(defaults);
            return defaults;
        }
        return configs;
    }

    private WorkflowConfig createConfig(String key, String value) {
        WorkflowConfig c = new WorkflowConfig();
        c.setConfigKey(key);
        c.setConfigValue(value);
        return c;
    }

    @PutMapping("/workflow")
    public String updateWorkflowConfig(@RequestBody List<WorkflowConfig> configs) {
        workflowConfigRepository.saveAll(configs);
        return "Workflow configuration updated";
    }

    // ================= BROADCAST NOTIFICATION =================

    @PostMapping("/notifications/broadcast")
    public String broadcastNotification(
            @RequestBody Map<String, String> body,
            @RequestHeader("Authorization") String token
    ) {
        String message = body.get("message");
        Long senderId = employeeService.getUserIdFromToken(token);
        notificationService.broadcast(message, senderId);
        return "Notification broadcast to all users";
    }
}