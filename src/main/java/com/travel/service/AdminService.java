//package com.travel.service;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.travel.dto.CreateUserRequest;
//import com.travel.dto.UserResponse;
//import com.travel.entity.AuditLog;
//import com.travel.entity.TravelPolicy;
//import com.travel.entity.User;
//import com.travel.repository.AuditLogRepository;
//import com.travel.repository.ExpenseRepository;
//import com.travel.repository.TravelPolicyRepository;
//import com.travel.repository.TravelRequestRepository;
//import com.travel.repository.UserRepository;
//
//@Service
//public class AdminService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private TravelRequestRepository travelRequestRepository;
//
//    @Autowired
//    private ExpenseRepository expenseRepository;
//
//    @Autowired
//    private TravelPolicyRepository policyRepository;
//
//    @Autowired
//    private AuditLogRepository auditRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    // ========================
//    // USER MANAGEMENT
//    // ========================
//
//    public UserResponse createUser(CreateUserRequest request) {
//
//        User user = new User();
//
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//
//        user.setPassword(
//                passwordEncoder.encode(request.getPassword())
//        );
//
//        user.setRole(request.getRole());
//        user.setDepartment(request.getDepartment());
//        user.setActive(true);
//
//        userRepository.save(user);
//
//        // AUDIT LOG
//        log(
//                "USER_CREATED",
//                "ADMIN",
//                "SUCCESS"
//        );
//
//        return new UserResponse(user);
//    }
//
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//    public UserResponse updateUser(
//            Long id,
//            CreateUserRequest request
//    ) {
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("User not found")
//                );
//
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//
//        if (request.getPassword() != null &&
//            !request.getPassword().isEmpty()) {
//
//            user.setPassword(
//                    passwordEncoder.encode(
//                            request.getPassword()
//                    )
//            );
//        }
//
//        user.setRole(request.getRole());
//        user.setDepartment(request.getDepartment());
//
//        userRepository.save(user);
//
//        // AUDIT LOG
//        log(
//                "USER_UPDATED",
//                "ADMIN",
//                "SUCCESS"
//        );
//
//        return new UserResponse(user);
//    }
//
//    public String deleteUser(Long id) {
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("User not found")
//                );
//
//        // SYSTEM ADMIN PROTECTION
//        if (user.getEmail().equals("admin@test.com")) {
//
//            throw new RuntimeException(
//                    "System Admin cannot be deleted"
//            );
//        }
//
//        userRepository.delete(user);
//
//        // AUDIT LOG
//        log(
//                "USER_DELETED",
//                "ADMIN",
//                "SUCCESS"
//        );
//
//        return "User deleted successfully";
//    }
//
//    // ========================
//    // TOGGLE USER STATUS
//    // ========================
//
//    public String toggleUserStatus(Long id) {
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("User not found")
//                );
//
//        user.setActive(!user.isActive());
//
//        userRepository.save(user);
//
//        // AUDIT LOG
//        log(
//                "USER_STATUS_UPDATED",
//                "ADMIN",
//                "SUCCESS"
//        );
//
//        return user.isActive()
//                ? "User Activated"
//                : "User Disabled";
//    }
//
//    // ========================
//    // TRAVEL POLICY MANAGEMENT
//    // ========================
//
//    public String setPolicy(
//            Double maxBudget,
//            String allowedClass
//    ) {
//
//        TravelPolicy policy = new TravelPolicy();
//
//        policy.setMaxBudget(maxBudget);
//        policy.setAllowedClass(allowedClass);
//        policy.setActive(true);
//
//        policyRepository.save(policy);
//
//        // AUDIT LOG
//        log(
//                "POLICY_CREATED",
//                "ADMIN",
//                "SUCCESS"
//        );
//
//        return "Policy saved successfully";
//    }
//
//    public List<TravelPolicy> getAllPolicies() {
//        return policyRepository.findAll();
//    }
//
//    public String togglePolicy(Long id) {
//
//        TravelPolicy policy = policyRepository.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("Policy not found")
//                );
//
//        policy.setActive(!policy.isActive());
//
//        policyRepository.save(policy);
//
//        // AUDIT LOG
//        log(
//                "POLICY_STATUS_UPDATED",
//                "ADMIN",
//                "SUCCESS"
//        );
//
//        return policy.isActive()
//                ? "Policy Activated"
//                : "Policy Disabled";
//    }
//
//    public String deletePolicy(Long id) {
//
//        TravelPolicy policy = policyRepository.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("Policy not found")
//                );
//
//        policyRepository.delete(policy);
//
//        // AUDIT LOG
//        log(
//                "POLICY_DELETED",
//                "ADMIN",
//                "SUCCESS"
//        );
//
//        return "Policy deleted successfully";
//    }
//
//    // ========================
//    // REPORTS
//    // ========================
//
//    public Map<String, Object> getAdminReport() {
//
//        Map<String, Object> report =
//                new HashMap<>();
//
//        report.put(
//                "totalUsers",
//                userRepository.count()
//        );
//
//        report.put(
//                "totalTravelRequests",
//                travelRequestRepository.count()
//        );
//
//        report.put(
//                "totalExpenses",
//                expenseRepository.count()
//        );
//
//        report.put(
//                "totalPolicies",
//                policyRepository.count()
//        );
//
//        return report;
//    }
//
//    // ========================
//    // AUDIT LOGS
//    // ========================
//
//    public List<AuditLog> getAuditLogs() {
//        return auditRepository.findAll();
//    }
//
//    public void log(
//            String action,
//            String user,
//            String status
//    ) {
//
//        AuditLog log = new AuditLog();
//
//        log.setAction(action);
//
//        log.setPerformedBy(user);
//
//        log.setStatus(status);
//
//        log.setTimestamp(
//                LocalDateTime.now()
//        );
//
//        auditRepository.save(log);
//    }
//}



package com.travel.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.dto.CreateUserRequest;
import com.travel.dto.UserResponse;
import com.travel.entity.AuditLog;
import com.travel.entity.TravelPolicy;
import com.travel.entity.TravelRequest;
import com.travel.entity.User;
import com.travel.enums.RequestStatus;
import com.travel.repository.AuditLogRepository;
import com.travel.repository.ExpenseRepository;
import com.travel.repository.TravelPolicyRepository;
import com.travel.repository.TravelRequestRepository;
import com.travel.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private TravelPolicyRepository policyRepository;

    @Autowired
    private AuditLogRepository auditRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // =====================================================
    // USER MANAGEMENT
    // =====================================================

    public UserResponse createUser(CreateUserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setDepartment(request.getDepartment());
        user.setActive(true);

        userRepository.save(user);

        log("USER_CREATED", "SYSTEM", "SUCCESS");

        return new UserResponse(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

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

        log("USER_UPDATED", "SYSTEM", "SUCCESS");

        return new UserResponse(user);
    }

    public String deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // System Admin protection
        if ("admin@test.com".equals(user.getEmail())) {
            throw new RuntimeException("System Admin cannot be deleted");
        }

        userRepository.delete(user);

        log("USER_DELETED", "SYSTEM", "SUCCESS");

        return "User deleted successfully";
    }

    public String toggleUserStatus(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(!user.isActive());

        userRepository.save(user);

        log("USER_STATUS_UPDATED", "SYSTEM", "SUCCESS");

        return user.isActive() ? "User Activated" : "User Disabled";
    }

    // =====================================================
    // TRAVEL POLICY MANAGEMENT
    // =====================================================

    public String setPolicy(Double maxBudget, String allowedClass) {

        TravelPolicy policy = new TravelPolicy();
        policy.setMaxBudget(maxBudget);
        policy.setAllowedClass(allowedClass);
        policy.setActive(true);

        policyRepository.save(policy);

        log("POLICY_CREATED", "SYSTEM", "SUCCESS");

        return "Policy saved successfully";
    }

    public List<TravelPolicy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public TravelPolicy updatePolicy(Long id, Double maxBudget, String allowedClass) {
        TravelPolicy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
        if (maxBudget != null) policy.setMaxBudget(maxBudget);
        if (allowedClass != null) policy.setAllowedClass(allowedClass);
        policyRepository.save(policy);
        log("POLICY_UPDATED", "SYSTEM", "SUCCESS");
        return policy;
    }

    public String togglePolicy(Long id) {

        TravelPolicy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setActive(!policy.isActive());

        policyRepository.save(policy);

        log("POLICY_STATUS_UPDATED", "SYSTEM", "SUCCESS");

        return policy.isActive() ? "Policy Activated" : "Policy Disabled";
    }

    public String deletePolicy(Long id) {

        TravelPolicy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policyRepository.delete(policy);

        log("POLICY_DELETED", "SYSTEM", "SUCCESS");

        return "Policy deleted successfully";
    }

    // =====================================================
    // POLICY VIOLATIONS
    // =====================================================

    public List<TravelRequest> getPolicyViolations() {
        return travelRequestRepository.findByPolicyViolatedTrue();
    }

    public String waiveViolation(Long id) {
        TravelRequest req = travelRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        req.setPolicyViolated(false);
        req.setPolicyViolationReason(null);
        if (req.getStatus() == RequestStatus.POLICY_VALIDATION) {
            req.setStatus(RequestStatus.MANAGER_REVIEW);
        }
        travelRequestRepository.save(req);
        log("VIOLATION_WAIVED", "ADMIN", "SUCCESS");
        return "Violation waived — request moved to manager review";
    }

    public String dismissRequest(Long id) {
        TravelRequest req = travelRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        req.setStatus(RequestStatus.DISMISSED);
        travelRequestRepository.save(req);
        log("REQUEST_DISMISSED", "ADMIN", "SUCCESS");
        return "Request dismissed";
    }

    // =====================================================
    // REPORTS
    // =====================================================

    public Map<String, Object> getAdminReport() {

        Map<String, Object> report = new HashMap<>();

        report.put("totalUsers", userRepository.count());
        report.put("totalTravelRequests", travelRequestRepository.count());
        report.put("totalExpenses", expenseRepository.count());
        report.put("totalPolicies", policyRepository.count());

        return report;
    }

    // =====================================================
    // AUDIT LOGGING (COMMON METHOD)
    // =====================================================

    public void log(String action, String performedBy, String status) {

        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setPerformedBy(performedBy);
        log.setStatus(status);
        log.setTimestamp(LocalDateTime.now());

        auditRepository.save(log);
    }
}