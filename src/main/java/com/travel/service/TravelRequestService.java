//package com.travel.service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.travel.dto.CreateTravelRequestDTO;
//import com.travel.entity.TravelRequest;
//import com.travel.entity.User;
//import com.travel.enums.RequestStatus;
//import com.travel.repository.TravelRequestRepository;
//import com.travel.repository.UserRepository;
//
//@Service
//public class TravelRequestService {
//
//    @Autowired
//    private TravelRequestRepository travelRequestRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private AuditLogService auditLogService;
//
//    // =========================
//    // CREATE REQUEST
//    // =========================
//
//    public String createRequest(
//            Long userId,
//            CreateTravelRequestDTO dto
//    ) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() ->
//                        new RuntimeException("User not found")
//                );
//
//        TravelRequest request = new TravelRequest();
//
//        request.setUser(user);
//
//        request.setDestination(dto.getDestination());
//
//        request.setStartDate(dto.getStartDate());
//
//        request.setEndDate(dto.getEndDate());
//
//        request.setPurpose(dto.getPurpose());
//
//        request.setBudget(dto.getBudget());
//
//        request.setStatus(RequestStatus.DRAFT);
//
//        request.setCreatedAt(LocalDateTime.now());
//
//        travelRequestRepository.save(request);
//
//        // AUDIT LOG
//        auditLogService.saveLog(
//                "TRAVEL_REQUEST_CREATED",
//                user.getEmail(),
//                "SUCCESS"
//        );
//
//        return "Travel request created successfully";
//    }
//
//    // =========================
//    // UPDATE DRAFT
//    // =========================
//
//    public String updateDraft(
//            Long requestId,
//            CreateTravelRequestDTO dto
//    ) {
//
//        TravelRequest request =
//                travelRequestRepository.findById(requestId)
//                        .orElseThrow(() ->
//                                new RuntimeException("Request not found")
//                        );
//
//        if (request.getStatus() != RequestStatus.DRAFT) {
//
//            throw new RuntimeException(
//                    "Only draft requests can be edited"
//            );
//        }
//
//        request.setDestination(dto.getDestination());
//
//        request.setStartDate(dto.getStartDate());
//
//        request.setEndDate(dto.getEndDate());
//
//        request.setPurpose(dto.getPurpose());
//
//        request.setBudget(dto.getBudget());
//
//        travelRequestRepository.save(request);
//
//        // AUDIT LOG
//        auditLogService.saveLog(
//                "TRAVEL_REQUEST_UPDATED",
//                request.getUser().getEmail(),
//                "SUCCESS"
//        );
//
//        return "Draft updated successfully";
//    }
//
//    // =========================
//    // SUBMIT REQUEST
//    // =========================
//
//    public String submitRequest(Long requestId) {
//
//        TravelRequest request =
//                travelRequestRepository.findById(requestId)
//                        .orElseThrow(() ->
//                                new RuntimeException("Request not found")
//                        );
//
//        request.setStatus(RequestStatus.SUBMITTED);
//
//        travelRequestRepository.save(request);
//
//        // AUDIT LOG
//        auditLogService.saveLog(
//                "TRAVEL_REQUEST_SUBMITTED",
//                request.getUser().getEmail(),
//                "SUCCESS"
//        );
//
//        return "Request submitted successfully";
//    }
//
//    // =========================
//    // GET USER REQUESTS
//    // =========================
//
//    public List<TravelRequest> getMyRequests(Long userId) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() ->
//                        new RuntimeException("User not found")
//                );
//
//        return travelRequestRepository.findByUser(user);
//    }
//
//    // =========================
//    // MANAGER APPROVE
//    // =========================
//
//    public String managerApprove(Long requestId) {
//
//        TravelRequest request =
//                travelRequestRepository.findById(requestId)
//                        .orElseThrow(() ->
//                                new RuntimeException("Request not found")
//                        );
//
//        request.setStatus(RequestStatus.MANAGER_APPROVED);
//
//        travelRequestRepository.save(request);
//
//        auditLogService.saveLog(
//                "MANAGER_APPROVED_REQUEST",
//                "MANAGER",
//                "SUCCESS"
//        );
//
//        return "Request approved by manager";
//    }
//
//    // =========================
//    // FINANCE APPROVE
//    // =========================
//
//    public String financeApprove(Long requestId) {
//
//        TravelRequest request =
//                travelRequestRepository.findById(requestId)
//                        .orElseThrow(() ->
//                                new RuntimeException("Request not found")
//                        );
//
//        request.setStatus(RequestStatus.FINANCE_APPROVED);
//
//        travelRequestRepository.save(request);
//
//        auditLogService.saveLog(
//                "FINANCE_APPROVED_REQUEST",
//                "FINANCE",
//                "SUCCESS"
//        );
//
//        return "Request approved by finance";
//    }
//
//    // =========================
//    // REJECT REQUEST
//    // =========================
//
//    public String rejectRequest(Long requestId) {
//
//        TravelRequest request =
//                travelRequestRepository.findById(requestId)
//                        .orElseThrow(() ->
//                                new RuntimeException("Request not found")
//                        );
//
//        request.setStatus(RequestStatus.REJECTED);
//
//        travelRequestRepository.save(request);
//
//        auditLogService.saveLog(
//                "TRAVEL_REQUEST_REJECTED",
//                "MANAGER",
//                "SUCCESS"
//        );
//
//        return "Request rejected";
//    }
//}



package com.travel.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dto.CreateTravelRequestDTO;
import com.travel.entity.TravelRequest;
import com.travel.entity.User;
import com.travel.enums.RequestStatus;
import com.travel.repository.TravelRequestRepository;
import com.travel.repository.UserRepository;

@Service
public class TravelRequestService {

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditLogService auditLogService;

    // =====================================================
    // CREATE REQUEST
    // STATUS = DRAFT
    // =====================================================

    public String createRequest(
            Long userId,
            CreateTravelRequestDTO dto
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        TravelRequest request = new TravelRequest();

        request.setUser(user);

        request.setSource(dto.getSource());

        request.setDestination(dto.getDestination());

        request.setPurpose(dto.getPurpose());

        request.setStartDate(dto.getStartDate());

        request.setEndDate(dto.getEndDate());

        request.setBudget(dto.getBudget());

        request.setTransportMode(dto.getTransportMode());

        request.setAccommodation(dto.getAccommodation());

        request.setDescription(dto.getDescription());

        request.setStatus(RequestStatus.DRAFT);

        request.setCreatedAt(LocalDateTime.now());

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "TRAVEL_REQUEST_CREATED",
                user.getEmail(),
                "SUCCESS"
        );

        return "Travel request created successfully";
    }

    // =====================================================
    // UPDATE DRAFT REQUEST
    // =====================================================

    public String updateDraft(
            Long requestId,
            CreateTravelRequestDTO dto
    ) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        if (request.getStatus() != RequestStatus.DRAFT) {

            throw new RuntimeException(
                    "Only draft requests can be edited"
            );
        }

        request.setSource(dto.getSource());

        request.setDestination(dto.getDestination());

        request.setPurpose(dto.getPurpose());

        request.setStartDate(dto.getStartDate());

        request.setEndDate(dto.getEndDate());

        request.setBudget(dto.getBudget());

        request.setTransportMode(dto.getTransportMode());

        request.setAccommodation(dto.getAccommodation());

        request.setDescription(dto.getDescription());

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "TRAVEL_REQUEST_UPDATED",
                request.getUser().getEmail(),
                "SUCCESS"
        );

        return "Draft updated successfully";
    }

    // =====================================================
    // SUBMIT REQUEST
    // DRAFT -> SUBMITTED
    // =====================================================

    public String submitRequest(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        if (request.getStatus() != RequestStatus.DRAFT) {

            throw new RuntimeException(
                    "Only draft requests can be submitted"
            );
        }

        request.setStatus(RequestStatus.SUBMITTED);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "TRAVEL_REQUEST_SUBMITTED",
                request.getUser().getEmail(),
                "SUCCESS"
        );

        return "Request submitted successfully";
    }

    // =====================================================
    // GET USER REQUESTS
    // =====================================================

    public List<TravelRequest> getMyRequests(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return travelRequestRepository.findByUser(user);
    }

    // =====================================================
    // MANAGER REVIEW
    // SUBMITTED -> MANAGER_REVIEW
    // =====================================================

    public String moveToManagerReview(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.MANAGER_REVIEW);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "REQUEST_SENT_TO_MANAGER",
                request.getUser().getEmail(),
                "SUCCESS"
        );

        return "Request moved to manager review";
    }

    // =====================================================
    // MANAGER APPROVE
    // MANAGER_REVIEW -> MANAGER_APPROVED
    // =====================================================

    public String managerApprove(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.MANAGER_APPROVED);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "MANAGER_APPROVED_REQUEST",
                "MANAGER",
                "SUCCESS"
        );

        return "Request approved by manager";
    }

    // =====================================================
    // MOVE TO FINANCE REVIEW
    // MANAGER_APPROVED -> FINANCE_REVIEW
    // =====================================================

    public String moveToFinanceReview(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.FINANCE_REVIEW);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "REQUEST_SENT_TO_FINANCE",
                "SYSTEM",
                "SUCCESS"
        );

        return "Request moved to finance review";
    }

    // =====================================================
    // FINANCE APPROVE
    // FINANCE_REVIEW -> FINANCE_APPROVED
    // =====================================================

    public String financeApprove(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.FINANCE_APPROVED);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "FINANCE_APPROVED_REQUEST",
                "FINANCE",
                "SUCCESS"
        );

        return "Request approved by finance";
    }

    // =====================================================
    // ITINERARY CREATED
    // FINANCE_APPROVED -> ITINERARY_CREATED
    // =====================================================

    public String itineraryCreated(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.ITINERARY_CREATED);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "ITINERARY_CREATED",
                "SYSTEM",
                "SUCCESS"
        );

        return "Itinerary created successfully";
    }

    // =====================================================
    // START TRAVEL
    // ITINERARY_CREATED -> TRAVEL_IN_PROGRESS
    // =====================================================

    public String startTravel(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.TRAVEL_IN_PROGRESS);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "TRAVEL_STARTED",
                request.getUser().getEmail(),
                "SUCCESS"
        );

        return "Travel started";
    }

    // =====================================================
    // EXPENSE SUBMITTED
    // TRAVEL_IN_PROGRESS -> EXPENSE_SUBMITTED
    // =====================================================

    public String expenseSubmitted(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.EXPENSE_SUBMITTED);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "EXPENSE_SUBMITTED",
                request.getUser().getEmail(),
                "SUCCESS"
        );

        return "Expense submitted";
    }

    // =====================================================
    // EXPENSE REVIEW
    // EXPENSE_SUBMITTED -> EXPENSE_REVIEW
    // =====================================================

    public String expenseReview(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.EXPENSE_REVIEW);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "EXPENSE_REVIEW_STARTED",
                "FINANCE",
                "SUCCESS"
        );

        return "Expense review started";
    }

    // =====================================================
    // REIMBURSED
    // EXPENSE_REVIEW -> REIMBURSED
    // =====================================================

    public String reimbursed(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.REIMBURSED);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "REIMBURSEMENT_COMPLETED",
                "FINANCE",
                "SUCCESS"
        );

        return "Reimbursement completed";
    }

    // =====================================================
    // COMPLETE WORKFLOW
    // REIMBURSED -> COMPLETED
    // =====================================================

    public String completeRequest(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.COMPLETED);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "WORKFLOW_COMPLETED",
                "SYSTEM",
                "SUCCESS"
        );

        return "Travel workflow completed";
    }

    // =====================================================
    // CANCEL REQUEST
    // =====================================================

    public String cancelRequest(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.CANCELLED);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "TRAVEL_REQUEST_CANCELLED",
                request.getUser().getEmail(),
                "SUCCESS"
        );

        return "Request cancelled";
    }

    // =====================================================
    // REJECT REQUEST
    // =====================================================

    public String rejectRequest(Long requestId) {

        TravelRequest request =
                travelRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found")
                        );

        request.setStatus(RequestStatus.REJECTED);

        travelRequestRepository.save(request);

        auditLogService.saveLog(
                "TRAVEL_REQUEST_REJECTED",
                "MANAGER/FINANCE",
                "SUCCESS"
        );

        return "Request rejected";
    }

}