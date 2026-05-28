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

import java.util.List;
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
        res.setStatus(req.getStatus());

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

        req.setStatus(RequestStatus.MANAGER_REVIEW);

        travelRequestRepository.save(req);

        return "Request submitted";
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
}
