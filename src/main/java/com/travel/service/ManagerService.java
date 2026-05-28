//package com.travel.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.travel.dto.TravelRequestResponse;
//import com.travel.entity.TravelRequest;
//import com.travel.enums.RequestStatus;
//import com.travel.repository.TravelRequestRepository;
//
//@Service
//public class ManagerService {
//
//    @Autowired
//    private TravelRequestRepository travelRequestRepository;
//
//    // ================= GET PENDING REQUESTS =================
//    public List<TravelRequestResponse> getPendingRequests() {
//
//        List<TravelRequest> requests =
//                travelRequestRepository
//                        .findByStatus(RequestStatus.SUBMITTED);
//
//        return requests.stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//
//    // ================= APPROVE / REJECT =================
//    public String reviewRequest(
//            Long id,
//            boolean approve,
//            String comment
//    ) {
//
//        TravelRequest request =
//                travelRequestRepository
//                        .findById(id)
//                        .orElseThrow(() ->
//                                new RuntimeException("Request not found"));
//
//        if (approve) {
//
//            request.setStatus(
//                    RequestStatus.MANAGER_APPROVED
//            );
//
//        } else {
//
//            request.setStatus(
//                    RequestStatus.REJECTED
//            );
//        }
//
//        request.setManagerComment(comment);
//
//        travelRequestRepository.save(request);
//
//        return approve
//                ? "Request approved successfully"
//                : "Request rejected successfully";
//    }
//
//    // ================= APPROVAL HISTORY =================
//    public List<TravelRequest> getApprovalHistory() {
//
//        return travelRequestRepository
//                .findAll()
//                .stream()
//                .filter(r ->
//
//                        r.getStatus() ==
//                        RequestStatus.MANAGER_APPROVED
//
//                        ||
//
//                        r.getStatus() ==
//                        RequestStatus.REJECTED
//                )
//                .collect(Collectors.toList());
//    }
//
//    // ================= DTO MAPPING =================
//    private TravelRequestResponse mapToDTO(
//            TravelRequest request
//    ) {
//
//        TravelRequestResponse dto =
//                new TravelRequestResponse();
//
//        dto.setId(request.getId());
//
//        dto.setDestination(
//                request.getDestination()
//        );
//
//        dto.setSource(
//                request.getSource()
//        );
//
//        dto.setPurpose(
//                request.getPurpose()
//        );
//
//        dto.setBudget(
//                request.getBudget()
//        );
//
//        dto.setStartDate(
//                request.getStartDate()
//        );
//
//        dto.setEndDate(
//                request.getEndDate()
//        );
//
//        dto.setStatus(
//                request.getStatus()
//        );
//
//        dto.setEmployeeName(
//                request.getUser().getName()
//        );
//
//        return dto;
//    }
//
//    // ================= TEAM ACTIVITY =================
//    public List<TravelRequestResponse> getTeamActivities() {
//
//        List<TravelRequest> requests =
//                travelRequestRepository.findAll();
//
//        return requests.stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//
//    // ================= TOTAL REQUESTS =================
//    public long getTotalRequests() {
//
//        return travelRequestRepository.count();
//    }
//
//    // ================= APPROVED REQUESTS =================
//    public long getApprovedRequests() {
//
//        return travelRequestRepository
//                .findAll()
//                .stream()
//                .filter(r -> r.getStatus() ==
//                        RequestStatus.MANAGER_APPROVED)
//                .count();
//    }
//
//    // ================= REJECTED REQUESTS =================
//    public long getRejectedRequests() {
//
//        return travelRequestRepository
//                .findAll()
//                .stream()
//                .filter(r -> r.getStatus() ==
//                        RequestStatus.REJECTED)
//                .count();
//    }
//
//    // ================= TOTAL BUDGET =================
//    public double getTotalBudget() {
//
//        return travelRequestRepository
//                .findAll()
//                .stream()
//                .mapToDouble(TravelRequest::getBudget)
//                .sum();
//    }
//
// 
//    
//}



package com.travel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dto.CreateTravelRequestDTO;
import com.travel.dto.TravelRequestResponse;
import com.travel.entity.TravelRequest;
import com.travel.enums.RequestStatus;
import com.travel.repository.TravelRequestRepository;

@Service
public class ManagerService {

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    // =====================================================
    // GET PENDING REQUESTS
    // SHOW SUBMITTED + MANAGER_REVIEW
    // =====================================================

    public List<TravelRequestResponse> getPendingRequests() {

        List<TravelRequest> requests =
                travelRequestRepository.findAll()
                        .stream()
                        .filter(r ->

                                r.getStatus() == RequestStatus.SUBMITTED

                                ||

                                r.getStatus() == RequestStatus.MANAGER_REVIEW
                        )
                        .collect(Collectors.toList());

        return requests.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // =====================================================
    // APPROVE / REJECT REQUEST
    // =====================================================

    public String reviewRequest(
            Long id,
            boolean approve,
            String comment
    ) {

        TravelRequest request =
                travelRequestRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found"));

        // ============================================
        // VALIDATION
        // ============================================

        if (

                request.getStatus() != RequestStatus.SUBMITTED

                &&

                request.getStatus() != RequestStatus.MANAGER_REVIEW
        ) {

            return "Only submitted requests can be reviewed";
        }

        // ============================================
        // APPROVE FLOW
        // ============================================

        if (approve) {

            // STEP 1
            request.setStatus(
                    RequestStatus.MANAGER_APPROVED
            );

            request.setManagerComment(comment);

            travelRequestRepository.save(request);

            // STEP 2
            request.setStatus(
                    RequestStatus.FINANCE_REVIEW
            );

        }

        // ============================================
        // REJECT FLOW
        // ============================================

        else {

            request.setStatus(
                    RequestStatus.REJECTED
            );
        }

        request.setManagerComment(comment);

        travelRequestRepository.save(request);

        return approve

                ? "Request approved and sent to finance"

                : "Request rejected successfully";
    }

    // =====================================================
    // APPROVAL HISTORY
    // =====================================================

    public List<TravelRequest> getApprovalHistory() {

        return travelRequestRepository
                .findAll()
                .stream()
                .filter(r ->

                        r.getStatus() == RequestStatus.MANAGER_APPROVED

                        ||

                        r.getStatus() == RequestStatus.FINANCE_REVIEW

                        ||

                        r.getStatus() == RequestStatus.FINANCE_APPROVED

                        ||

                        r.getStatus() == RequestStatus.ITINERARY_CREATED

                        ||

                        r.getStatus() == RequestStatus.TRAVEL_IN_PROGRESS

                        ||

                        r.getStatus() == RequestStatus.EXPENSE_SUBMITTED

                        ||

                        r.getStatus() == RequestStatus.EXPENSE_REVIEW

                        ||

                        r.getStatus() == RequestStatus.REIMBURSED

                        ||

                        r.getStatus() == RequestStatus.COMPLETED

                        ||

                        r.getStatus() == RequestStatus.REJECTED
                )
                .collect(Collectors.toList());
    }

    // =====================================================
    // DTO MAPPING
    // =====================================================

    private TravelRequestResponse mapToDTO(
            TravelRequest request
    ) {

        TravelRequestResponse dto =
                new TravelRequestResponse();

        dto.setId(request.getId());

        dto.setDestination(
                request.getDestination()
        );

        dto.setSource(
                request.getSource()
        );

        dto.setPurpose(
                request.getPurpose()
        );

        dto.setBudget(
                request.getBudget()
        );

        dto.setStartDate(
                request.getStartDate()
        );

        dto.setEndDate(
                request.getEndDate()
        );

        dto.setStatus(
                request.getStatus()
        );

        dto.setEmployeeName(
                request.getUser().getName()
        );

        dto.setDocumentUrl(
                request.getDocumentUrl()
        );

        dto.setPriority(
                request.getPriority() != null ? request.getPriority().name() : null
        );

        dto.setPolicyViolated(
                request.getPolicyViolated()
        );

        dto.setPolicyViolationReason(
                request.getPolicyViolationReason()
        );

        return dto;
    }

    // =====================================================
    // TEAM ACTIVITY
    // =====================================================

    public List<TravelRequestResponse> getTeamActivities() {

        List<TravelRequest> requests =
                travelRequestRepository.findAll();

        return requests.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // =====================================================
    // TOTAL REQUESTS
    // =====================================================

    public long getTotalRequests() {

        return travelRequestRepository.count();
    }

    // =====================================================
    // APPROVED REQUESTS
    // =====================================================

    public long getApprovedRequests() {

        return travelRequestRepository
                .findAll()
                .stream()
                .filter(r ->

                        r.getStatus() == RequestStatus.MANAGER_APPROVED

                        ||

                        r.getStatus() == RequestStatus.FINANCE_REVIEW

                        ||

                        r.getStatus() == RequestStatus.FINANCE_APPROVED

                        ||

                        r.getStatus() == RequestStatus.ITINERARY_CREATED

                        ||

                        r.getStatus() == RequestStatus.TRAVEL_IN_PROGRESS

                        ||

                        r.getStatus() == RequestStatus.EXPENSE_SUBMITTED

                        ||

                        r.getStatus() == RequestStatus.EXPENSE_REVIEW

                        ||

                        r.getStatus() == RequestStatus.REIMBURSED

                        ||

                        r.getStatus() == RequestStatus.COMPLETED
                )
                .count();
    }

    // =====================================================
    // REJECTED REQUESTS
    // =====================================================

    public long getRejectedRequests() {

        return travelRequestRepository
                .findAll()
                .stream()
                .filter(r ->

                        r.getStatus() == RequestStatus.REJECTED
                )
                .count();
    }

    // =====================================================
    // TOTAL BUDGET
    // =====================================================

    public double getTotalBudget() {

        return travelRequestRepository
                .findAll()
                .stream()
                .mapToDouble(TravelRequest::getBudget)
                .sum();
    }

    // =====================================================
    // EDIT REQUEST (MANAGER)
    // =====================================================
    public String editRequest(Long requestId, CreateTravelRequestDTO dto) {

        TravelRequest req = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (dto.getSource() != null) req.setSource(dto.getSource());
        if (dto.getDestination() != null) req.setDestination(dto.getDestination());
        if (dto.getPurpose() != null) req.setPurpose(dto.getPurpose());
        if (dto.getStartDate() != null) req.setStartDate(dto.getStartDate());
        if (dto.getEndDate() != null) req.setEndDate(dto.getEndDate());
        if (dto.getBudget() != null) req.setBudget(dto.getBudget());
        if (dto.getTransportMode() != null) req.setTransportMode(dto.getTransportMode());
        if (dto.getAccommodation() != null) req.setAccommodation(dto.getAccommodation());
        if (dto.getDescription() != null) req.setDescription(dto.getDescription());
        if (dto.getDocumentUrl() != null) req.setDocumentUrl(dto.getDocumentUrl());

        travelRequestRepository.save(req);

        return "Request updated successfully";
    }

}