package com.travel.dto;

import java.time.LocalDate;

import com.travel.enums.RequestStatus;

public class TravelRequestResponse {

    private Long id;

    private String source;

    private String destination;

    private String purpose;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double budget;

    private String transportMode;

    private String accommodation;

    private String description;

    private String documentUrl;

    private String priority;

    private Double actualExpense;

    private Boolean policyViolated;

    private String policyViolationReason;

    private RequestStatus status;

    private String managerComment;

    private String financeComment;

    private String employeeName;

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Double getActualExpense() {
        return actualExpense;
    }

    public void setActualExpense(Double actualExpense) {
        this.actualExpense = actualExpense;
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}


