//package com.travel.dto;
//
//public class ItineraryDTO {
//
//    private Integer dayNumber;
//
//    private String location;
//
//    private String activity;
//
//    private String hotelName;
//
//    private String notes;
//
//    private Long travelRequestId;
//
//    // GETTERS & SETTERS
//
//    public Integer getDayNumber() {
//        return dayNumber;
//    }
//
//    public void setDayNumber(Integer dayNumber) {
//        this.dayNumber = dayNumber;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getActivity() {
//        return activity;
//    }
//
//    public void setActivity(String activity) {
//        this.activity = activity;
//    }
//
//    public String getHotelName() {
//        return hotelName;
//    }
//
//    public void setHotelName(String hotelName) {
//        this.hotelName = hotelName;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    public Long getTravelRequestId() {
//        return travelRequestId;
//    }
//
//    public void setTravelRequestId(Long travelRequestId) {
//        this.travelRequestId = travelRequestId;
//    }
//}




package com.travel.dto;

import java.time.LocalDate;

public class ItineraryDTO {

    // =====================================================
    // DAY DETAILS
    // =====================================================
    private Integer dayNumber;

    private LocalDate travelDate;

    // =====================================================
    // LOCATION DETAILS
    // =====================================================
    private String location;

    private String activity;

    // =====================================================
    // HOTEL DETAILS
    // =====================================================
    private String hotelName;

    // =====================================================
    // EXTRA NOTES
    // =====================================================
    private String notes;

    // =====================================================
    // REQUEST LINK
    // =====================================================
    private Long travelRequestId;

    // =====================================================
    // OPTIONAL WORKFLOW FLAGS (SAFE EXTENSION)
    // =====================================================
    private Boolean isCompleted;

    private Boolean isTravelStarted;

    // =====================================================
    // GETTERS & SETTERS
    // =====================================================

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getTravelRequestId() {
        return travelRequestId;
    }

    public void setTravelRequestId(Long travelRequestId) {
        this.travelRequestId = travelRequestId;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean getIsTravelStarted() {
        return isTravelStarted;
    }

    public void setIsTravelStarted(Boolean isTravelStarted) {
        this.isTravelStarted = isTravelStarted;
    }
}