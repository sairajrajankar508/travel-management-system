package com.travel.dto;

public class ItineraryDTO {

    // =====================================================
    // DAY DETAILS
    // =====================================================
    private Integer dayNumber;

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
    // GETTERS & SETTERS
    // =====================================================

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
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
}
