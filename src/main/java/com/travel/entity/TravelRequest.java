//package com.travel.entity;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import com.travel.enums.RequestStatus;
//
//import jakarta.persistence.*;
//
//@Entity
//public class TravelRequest {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // USER
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    // BASIC DETAILS
//    private String source;
//
//    private String destination;
//
//    private String purpose;
//
//    // DATES
//    private LocalDate startDate;
//
//    private LocalDate endDate;
//
//    // BUDGET
//    private Double budget;
//
//    // EXTRA DETAILS
//    private String transportMode;
//
//    private String accommodation;
//
//    @Column(length = 2000)
//    private String description;
//
//    
//    // STATUS
//    @Enumerated(EnumType.STRING)
//    private RequestStatus status;
//
//    // CREATED TIME
//    private LocalDateTime createdAt;
//
//    @Column(length = 1000)
//    private String managerComment;
//    
//    
//    // =========================
//    // GETTERS & SETTERS
//    // =========================
//
//    public String getManagerComment() {
//		return managerComment;
//	}
//
//	public void setManagerComment(String managerComment) {
//		this.managerComment = managerComment;
//	}
//
//	public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getDestination() {
//        return destination;
//    }
//
//    public void setDestination(String destination) {
//        this.destination = destination;
//    }
//
//    public String getPurpose() {
//        return purpose;
//    }
//
//    public void setPurpose(String purpose) {
//        this.purpose = purpose;
//    }
//
//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDate endDate) {
//        this.endDate = endDate;
//    }
//
//    public Double getBudget() {
//        return budget;
//    }
//
//    public void setBudget(Double budget) {
//        this.budget = budget;
//    }
//
//    public String getTransportMode() {
//        return transportMode;
//    }
//
//    public void setTransportMode(String transportMode) {
//        this.transportMode = transportMode;
//    }
//
//    public String getAccommodation() {
//        return accommodation;
//    }
//
//    public void setAccommodation(String accommodation) {
//        this.accommodation = accommodation;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public RequestStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(RequestStatus status) {
//        this.status = status;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//
//}



package com.travel.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import com.travel.enums.Priority;
import com.travel.enums.RequestStatus;

@Entity
@Table(name = "travel_request")
public class TravelRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= USER =================
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ================= BASIC DETAILS =================
    private String source;

    private String destination;

    private String purpose;

    // ================= DATES =================
    private LocalDate startDate;

    private LocalDate endDate;

    // ================= BUDGET =================
    private Double budget;

    // ================= EXTRA DETAILS =================
    private String transportMode;

    private String accommodation;

    @Column(length = 3000)
    private String description;

    // ================= STATUS =================
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    // ================= PRIORITY =================
    @Enumerated(EnumType.STRING)
    private Priority priority;

    // ================= POLICY VIOLATION =================
    private Boolean policyViolated = false;

    @Column(length = 1000)
    private String policyViolationReason;

    // ================= DOCUMENT =================
    private String documentUrl;

    // ================= MANAGER =================
    @Column(length = 1000)
    private String managerComment;

    // ================= FINANCE =================
    @Column(length = 1000)
    private String financeComment;

    private Double approvedAmount;

    // ================= ITINERARY =================
    private Boolean itineraryCreated = false;

    // ================= TRAVEL =================
    private Boolean travelStarted = false;

    // ================= EXPENSE =================
    private Boolean expenseSubmitted = false;

    // ================= PAYMENT =================
    private Boolean reimbursed = false;

    // ================= TIMESTAMPS =================
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Boolean getPolicyViolated() {
        return policyViolated;
    }

    public void setPolicyViolated(Boolean policyViolated) {
        this.policyViolated = policyViolated;
    }

    public String getPolicyViolationReason() {
        return policyViolationReason;
    }

    public void setPolicyViolationReason(String policyViolationReason) {
        this.policyViolationReason = policyViolationReason;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getManagerComment() {
        return managerComment;
    }

    public void setManagerComment(String managerComment) {
        this.managerComment = managerComment;
    }

    public String getFinanceComment() {
        return financeComment;
    }

    public void setFinanceComment(String financeComment) {
        this.financeComment = financeComment;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public Boolean getItineraryCreated() {
        return itineraryCreated;
    }

    public void setItineraryCreated(Boolean itineraryCreated) {
        this.itineraryCreated = itineraryCreated;
    }

    public Boolean getTravelStarted() {
        return travelStarted;
    }

    public void setTravelStarted(Boolean travelStarted) {
        this.travelStarted = travelStarted;
    }

    public Boolean getExpenseSubmitted() {
        return expenseSubmitted;
    }

    public void setExpenseSubmitted(Boolean expenseSubmitted) {
        this.expenseSubmitted = expenseSubmitted;
    }

    public Boolean getReimbursed() {
        return reimbursed;
    }

    public void setReimbursed(Boolean reimbursed) {
        this.reimbursed = reimbursed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}