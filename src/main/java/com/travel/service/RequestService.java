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

    // OVERRIDE STATUS (FIXED WITH YOUR ENUM)
    public String overrideStatus(Long id, String status) {

        TravelRequest request = travelRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // 🔥 Convert String → Enum safely using YOUR enum
        RequestStatus requestStatus;

        try {
            requestStatus = RequestStatus.valueOf(status.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException(
                    "Invalid status! Allowed: DRAFT, SUBMITTED, MANAGER_APPROVED, REJECTED, FINANCE_APPROVED, COMPLETED"
            );
        }

        request.setStatus(requestStatus);

        travelRequestRepository.save(request);

        return "Request updated to " + requestStatus;
    }
}