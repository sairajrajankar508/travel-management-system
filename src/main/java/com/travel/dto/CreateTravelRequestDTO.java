//package com.travel.dto;
//
//import java.time.LocalDate;
//
//public class CreateTravelRequestDTO {
//
//    private String source;
//
//    private String destination;
//
//    private LocalDate startDate;
//
//    private LocalDate endDate;
//
//    private String purpose;
//
//    private Double budget;
//
//    private String transportMode;
//
//    private String accommodation;
//
//    private String description;
//
//    // GETTERS & SETTERS
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
//    public String getPurpose() {
//        return purpose;
//    }
//
//    public void setPurpose(String purpose) {
//        this.purpose = purpose;
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
//}




package com.travel.dto;

import java.time.LocalDate;

public class CreateTravelRequestDTO {

    // =====================================================
    // BASIC DETAILS
    // =====================================================

    private String source;

    private String destination;

    private String purpose;

    // =====================================================
    // TRAVEL DATES
    // =====================================================

    private LocalDate startDate;

    private LocalDate endDate;

    // =====================================================
    // BUDGET
    // =====================================================

    private Double budget;

    // =====================================================
    // TRAVEL DETAILS
    // =====================================================

    private String transportMode;

    private String accommodation;

    private String travelClass;

    // =====================================================
    // EXTRA DETAILS
    // =====================================================

    private String description;

    private String documentUrl;

    private String priority;

    private Boolean internationalTravel;

    private Integer totalDays;

    // =====================================================
    // GETTERS & SETTERS
    // =====================================================

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

        calculateTotalDays();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;

        calculateTotalDays();
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

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
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

    public Boolean getInternationalTravel() {
        return internationalTravel;
    }

    public void setInternationalTravel(Boolean internationalTravel) {
        this.internationalTravel = internationalTravel;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    // =====================================================
    // AUTO TOTAL DAYS CALCULATION
    // =====================================================

    private void calculateTotalDays() {

        if (startDate != null && endDate != null) {

            this.totalDays =
                    (int) (
                            endDate.toEpochDay()
                            - startDate.toEpochDay()
                    ) + 1;
        }
    }
}