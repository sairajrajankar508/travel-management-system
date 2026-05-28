//package com.travel.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.travel.entity.TravelRequest;
//import com.travel.enums.RequestStatus;
//import com.travel.repository.TravelRequestRepository;
//
//@Service
//public class RequestService {
//
//    @Autowired
//    private TravelRequestRepository travelRequestRepository;
//
//    // GET ALL REQUESTS
//    public List<TravelRequest> getAllRequests() {
//        return travelRequestRepository.findAll();
//    }
//
//    // OVERRIDE STATUS (FIXED WITH YOUR ENUM)
//    public String overrideStatus(Long id, String status) {
//
//        TravelRequest request = travelRequestRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Request not found"));
//
//        // 🔥 Convert String → Enum safely using YOUR enum
//        RequestStatus requestStatus;
//
//        try {
//            requestStatus = RequestStatus.valueOf(status.toUpperCase());
//        } catch (Exception e) {
//            throw new RuntimeException(
//                    "Invalid status! Allowed: DRAFT, SUBMITTED, MANAGER_APPROVED, REJECTED, FINANCE_APPROVED, COMPLETED"
//            );
//        }
//
//        request.setStatus(requestStatus);
//
//        travelRequestRepository.save(request);
//
//        return "Request updated to " + requestStatus;
//    }
//}



package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.TravelRequest;
import com.travel.enums.RequestStatus;
import com.travel.repository.TravelRequestRepository;

@Service
public class RequestService {

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    // GET ALL REQUESTS
    public List<TravelRequest> getAllRequests() {
        return travelRequestRepository.findAll();
    }

    // OVERRIDE STATUS (SAFE ENUM HANDLING)
    public String overrideStatus(Long id, String status) {

        TravelRequest request = travelRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        RequestStatus requestStatus;

        try {
            requestStatus = RequestStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                    "Invalid status! Allowed values: DRAFT, SUBMITTED, MANAGER_REVIEW, MANAGER_APPROVED, FINANCE_REVIEW, FINANCE_APPROVED, ITINERARY_CREATED, TRAVEL_IN_PROGRESS, EXPENSE_SUBMITTED, EXPENSE_REVIEW, REIMBURSED, COMPLETED, CANCELLED, REJECTED"
            );
        }

        request.setStatus(requestStatus);
        travelRequestRepository.save(request);

        return "Request updated to " + requestStatus;
    }
}