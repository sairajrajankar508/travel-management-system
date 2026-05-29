//package com.travel.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.travel.dto.*;
//import com.travel.entity.*;
//import com.travel.enums.*;
//import com.travel.repository.*;
//import com.travel.util.JwtUtil;
//
//@Service
//public class EmployeeService {
//
//    @Autowired
//    private TravelRequestRepository travelRequestRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ExpenseRepository expenseRepository;
//
//    // ================= TOKEN SAFE =================
//    public Long getUserIdFromToken(String token) {
//
//        if (token == null || token.isEmpty()) {
//            throw new RuntimeException("Token missing");
//        }
//
//        if (token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        }
//
//        String email = JwtUtil.extractEmail(token);
//
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return user.getId();
//    }
//
//    // ================= CREATE REQUEST =================
//    public String createRequest(CreateTravelRequestDTO dto, String token) {
//
//        Long userId = getUserIdFromToken(token);
//
//        User user = userRepository.findById(userId)
//                .orElseThrow();
//
//        TravelRequest request = new TravelRequest();
//
//        request.setSource(dto.getSource());
//        request.setDestination(dto.getDestination());
//        request.setPurpose(dto.getPurpose());
//        request.setStartDate(dto.getStartDate());
//        request.setEndDate(dto.getEndDate());
//        request.setBudget(dto.getBudget());
//        request.setTransportMode(dto.getTransportMode());
//        request.setAccommodation(dto.getAccommodation());
//        request.setDescription(dto.getDescription());
//        request.setStatus(RequestStatus.DRAFT);
//        request.setUser(user);
//
//        travelRequestRepository.save(request);
//
//        return "Travel request created successfully";
//    }
//
//    // ================= GET REQUESTS =================
//    public List<TravelRequestResponse> getMyRequests(String token) {
//
//        Long userId = getUserIdFromToken(token);
//
//        User user = userRepository.findById(userId).orElseThrow();
//
//        return travelRequestRepository.findByUser(user)
//                .stream()
//                .map(req -> {
//                    TravelRequestResponse res = new TravelRequestResponse();
//
//                    res.setId(req.getId());
//                    res.setSource(req.getSource());
//                    res.setDestination(req.getDestination());
//                    res.setPurpose(req.getPurpose());
//                    res.setStartDate(req.getStartDate());
//                    res.setEndDate(req.getEndDate());
//                    res.setBudget(req.getBudget());
//                    res.setTransportMode(req.getTransportMode());
//                    res.setAccommodation(req.getAccommodation());
//                    res.setDescription(req.getDescription());
//                    res.setStatus(req.getStatus());
//
//                    return res;
//                })
//                .collect(Collectors.toList());
//    }
//
//    // ================= EDIT REQUEST =================
//    public String editDraftRequest(Long requestId, CreateTravelRequestDTO dto, String token) {
//
//        Long userId = getUserIdFromToken(token);
//
//        TravelRequest request = travelRequestRepository.findById(requestId)
//                .orElseThrow(() -> new RuntimeException("Request not found"));
//
//        if (!request.getUser().getId().equals(userId)) {
//            return "Unauthorized";
//        }
//
//        if (request.getStatus() != RequestStatus.DRAFT) {
//            return "Only draft can be edited";
//        }
//
//        request.setSource(dto.getSource());
//        request.setDestination(dto.getDestination());
//        request.setPurpose(dto.getPurpose());
//        request.setStartDate(dto.getStartDate());
//        request.setEndDate(dto.getEndDate());
//        request.setBudget(dto.getBudget());
//        request.setTransportMode(dto.getTransportMode());
//        request.setAccommodation(dto.getAccommodation());
//        request.setDescription(dto.getDescription());
//
//        travelRequestRepository.save(request);
//
//        return "Request updated successfully";
//    }
//
//    // ================= SUBMIT REQUEST =================
//    public String submitRequest(Long requestId) {
//
//        TravelRequest request = travelRequestRepository.findById(requestId)
//                .orElseThrow(() -> new RuntimeException("Request not found"));
//
//        request.setStatus(RequestStatus.SUBMITTED);
//
//        travelRequestRepository.save(request);
//
//        return "Request submitted successfully";
//    }
//
//    // ================= DELETE REQUEST =================
//    public String deleteRequest(Long requestId, String token) {
//
//        Long userId = getUserIdFromToken(token);
//
//        TravelRequest request = travelRequestRepository.findById(requestId)
//                .orElseThrow(() -> new RuntimeException("Request not found"));
//
//        if (!request.getUser().getId().equals(userId)) {
//            return "Unauthorized";
//        }
//
//        travelRequestRepository.delete(request);
//
//        return "Request deleted successfully";
//    }
//
//    // ================= EXPENSE DELETE =================
//    public String deleteExpense(Long expenseId, Long userId) {
//
//        Expense expense = expenseRepository.findById(expenseId)
//                .orElseThrow(() -> new RuntimeException("Expense not found"));
//
//        if (!expense.getUser().getId().equals(userId)) {
//            return "Unauthorized";
//        }
//
//        expenseRepository.delete(expense);
//
//        return "Expense deleted successfully";
//    }
//
// // ================= PROFILE GET =================
//    public ProfileResponse getProfile(Long userId) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        ProfileResponse res = new ProfileResponse();
//
//        res.setName(user.getName());
//        res.setEmail(user.getEmail());
//        res.setRole(user.getRole() != null ? user.getRole().name() : null);
//
//        return res;
//    }
//
//    @Autowired
//    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
//
//    // ================= PROFILE UPDATE (SECURE) =================
//    public String updateProfile(Long userId, ProfileUpdateDTO dto) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // NAME UPDATE
//        if (dto.getName() != null && !dto.getName().isEmpty()) {
//            user.setName(dto.getName());
//        }
//
//        // EMAIL UPDATE
//        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
//            user.setEmail(dto.getEmail());
//        }
//
//        // PASSWORD UPDATE (HASHED 🔥)
//        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
//
//            String hashedPassword = passwordEncoder.encode(dto.getPassword());
//
//            user.setPassword(hashedPassword);
//        }
//
//        userRepository.save(user);
//
//        return "Profile updated successfully";
//    }
//
//}



package com.travel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.dto.*;
import com.travel.entity.*;
import com.travel.enums.*;
import com.travel.repository.*;
import com.travel.util.JwtUtil;

@Service
public class EmployeeService {

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private com.travel.repository.TravelPolicyRepository travelPolicyRepository;

    // =====================================================
    // TOKEN UTILITY
    // =====================================================
    public Long getUserIdFromToken(String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = JwtUtil.extractEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getId();
    }

    // =====================================================
    // 1. CREATE REQUEST
    // =====================================================
    public String createRequest(CreateTravelRequestDTO dto, String token) {

        Long userId = getUserIdFromToken(token);

        User user = userRepository.findById(userId).orElseThrow();

        TravelRequest req = new TravelRequest();

        req.setUser(user);
        req.setSource(dto.getSource());
        req.setDestination(dto.getDestination());
        req.setPurpose(dto.getPurpose());
        req.setStartDate(dto.getStartDate());
        req.setEndDate(dto.getEndDate());
        req.setBudget(dto.getBudget());
        req.setTransportMode(dto.getTransportMode());
        req.setAccommodation(dto.getAccommodation());
        req.setDescription(dto.getDescription());
        req.setDocumentUrl(dto.getDocumentUrl());

        if (dto.getPriority() != null) {
            req.setPriority(Priority.valueOf(dto.getPriority()));
        }

        // ================= POLICY CHECK =================
        List<com.travel.entity.TravelPolicy> policies = travelPolicyRepository.findByActiveTrue();
        StringBuilder violations = new StringBuilder();

        for (com.travel.entity.TravelPolicy policy : policies) {
            if (dto.getBudget() != null && policy.getMaxBudget() != null
                    && dto.getBudget() > policy.getMaxBudget()) {
                violations.append("Budget ₹").append(dto.getBudget())
                        .append(" exceeds policy limit of ₹").append(policy.getMaxBudget()).append(". ");
            }
        }

        if (violations.length() > 0) {
            req.setPolicyViolated(true);
            req.setPolicyViolationReason(violations.toString().trim());
        } else {
            req.setPolicyViolated(false);
        }

        req.setStatus(RequestStatus.DRAFT);

        travelRequestRepository.save(req);

        return "Travel request created successfully";
    }

    // =====================================================
    // 2. GET MY REQUESTS
    // =====================================================
    public List<TravelRequestResponse> getMyRequests(String token) {

        Long userId = getUserIdFromToken(token);

        User user = userRepository.findById(userId).orElseThrow();

        return travelRequestRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TravelRequestResponse mapToResponse(TravelRequest req) {

        TravelRequestResponse res = new TravelRequestResponse();

        res.setId(req.getId());
        res.setSource(req.getSource());
        res.setDestination(req.getDestination());
        res.setPurpose(req.getPurpose());
        res.setStartDate(req.getStartDate());
        res.setEndDate(req.getEndDate());
        res.setBudget(req.getBudget());
        res.setDocumentUrl(req.getDocumentUrl());
        res.setPriority(req.getPriority() != null ? req.getPriority().name() : null);
        res.setPolicyViolated(req.getPolicyViolated());
        res.setPolicyViolationReason(req.getPolicyViolationReason());
        res.setStatus(req.getStatus());

        Double actual = expenseRepository.findByTravelRequest(req)
                .stream()
                .mapToDouble(com.travel.entity.Expense::getAmount)
                .sum();
        res.setActualExpense(actual);

        return res;
    }

    // =====================================================
    // 3. EDIT DRAFT REQUEST
    // =====================================================
    public String editDraftRequest(Long requestId, CreateTravelRequestDTO dto, String token) {

        Long userId = getUserIdFromToken(token);

        TravelRequest req = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!req.getUser().getId().equals(userId)) {
            return "Unauthorized";
        }

        if (req.getStatus() != RequestStatus.DRAFT) {
            return "Only draft can be edited";
        }

        req.setSource(dto.getSource());
        req.setDestination(dto.getDestination());
        req.setPurpose(dto.getPurpose());
        req.setStartDate(dto.getStartDate());
        req.setEndDate(dto.getEndDate());
        req.setBudget(dto.getBudget());

        travelRequestRepository.save(req);

        return "Request updated";
    }

    // =====================================================
    // 4. SUBMIT REQUEST
    // =====================================================
    public String submitRequest(Long requestId) {

        TravelRequest req = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (req.getStatus() != RequestStatus.DRAFT) {
            return "Only draft can be submitted";
        }

        // STEP 1 — Set SUBMITTED
        req.setStatus(RequestStatus.SUBMITTED);

        // STEP 2 — Re-check policy violations
        List<com.travel.entity.TravelPolicy> policies = travelPolicyRepository.findByActiveTrue();
        StringBuilder violations = new StringBuilder();

        for (com.travel.entity.TravelPolicy policy : policies) {
            if (req.getBudget() != null && policy.getMaxBudget() != null
                    && req.getBudget() > policy.getMaxBudget()) {
                violations.append("Budget ₹").append(req.getBudget())
                        .append(" exceeds policy limit of ₹").append(policy.getMaxBudget()).append(". ");
            }
        }

        if (violations.length() > 0) {
            req.setPolicyViolated(true);
            req.setPolicyViolationReason(violations.toString().trim());
            req.setStatus(RequestStatus.POLICY_VALIDATION);
        } else {
            req.setPolicyViolated(false);
            req.setPolicyViolationReason(null);
            req.setStatus(RequestStatus.MANAGER_REVIEW);
        }

        travelRequestRepository.save(req);

        return violations.length() > 0
                ? "Request submitted — pending policy validation"
                : "Request submitted for manager approval";
    }

    // =====================================================
    // 5. CANCEL REQUEST (soft cancel)
    // =====================================================
    public String cancelRequest(Long requestId, String token) {

        Long userId = getUserIdFromToken(token);

        TravelRequest req = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!req.getUser().getId().equals(userId)) {
            return "Unauthorized";
        }

        req.setStatus(RequestStatus.CANCELLED);

        travelRequestRepository.save(req);

        return "Request cancelled";
    }

    // =====================================================
    // 6. DELETE REQUEST (hard delete)
    // =====================================================
    public String deleteRequest(Long requestId, String token) {

        Long userId = getUserIdFromToken(token);

        TravelRequest req = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!req.getUser().getId().equals(userId)) {
            return "Unauthorized";
        }

        if (req.getStatus() != RequestStatus.DRAFT) {
            return "Only draft can be deleted";
        }

        travelRequestRepository.delete(req);

        return "Request deleted";
    }

    // =====================================================
    // 6. START TRAVEL
    // =====================================================
    public String startTravel(Long requestId, String token) {

        Long userId = getUserIdFromToken(token);

        TravelRequest req = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!req.getUser().getId().equals(userId)) {
            return "Unauthorized";
        }

        req.setStatus(RequestStatus.TRAVEL_IN_PROGRESS);

        travelRequestRepository.save(req);

        return "Travel started";
    }

    // =====================================================
    // 7. COMPLETE TRAVEL
    // =====================================================
    public String completeTravel(Long requestId, String token) {

        Long userId = getUserIdFromToken(token);

        TravelRequest req = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!req.getUser().getId().equals(userId)) {
            return "Unauthorized";
        }

        req.setStatus(RequestStatus.COMPLETED);

        travelRequestRepository.save(req);

        return "Travel completed";
    }

    // =====================================================
    // 8. PROFILE GET
    // =====================================================
    public ProfileResponse getProfile(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProfileResponse res = new ProfileResponse();

        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setRole(user.getRole() != null ? user.getRole().name() : null);

        return res;
    }

    // =====================================================
    // 9. PROFILE UPDATE
    // =====================================================
    public String updateProfile(Long userId, ProfileUpdateDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());

        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        userRepository.save(user);

        return "Profile updated";
    }

    // =====================================================
    // 10. DASHBOARD SUMMARY
    // =====================================================
    public Map<String, Object> getDashboard(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<TravelRequest> requests = travelRequestRepository.findByUser(user);

        long total = requests.size();
        long pending = requests.stream()
                .filter(r -> r.getStatus() == RequestStatus.SUBMITTED || r.getStatus() == RequestStatus.DRAFT)
                .count();
        long approved = requests.stream()
                .filter(r -> r.getStatus() == RequestStatus.MANAGER_APPROVED || r.getStatus() == RequestStatus.FINANCE_APPROVED)
                .count();
        long rejected = requests.stream()
                .filter(r -> r.getStatus() == RequestStatus.REJECTED)
                .count();
        double totalBudget = requests.stream().mapToDouble(TravelRequest::getBudget).sum();

        List<TravelRequestResponse> upcomingTrips = requests.stream()
                .filter(r -> (r.getStatus() == RequestStatus.MANAGER_APPROVED || r.getStatus() == RequestStatus.FINANCE_APPROVED)
                        && !r.getStartDate().isBefore(java.time.LocalDate.now()))
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("totalRequests", total);
        dashboard.put("pendingRequests", pending);
        dashboard.put("approvedRequests", approved);
        dashboard.put("rejectedRequests", rejected);
        dashboard.put("totalBudget", totalBudget);
        dashboard.put("upcomingTrips", upcomingTrips);

        return dashboard;
    }
}
