package com.travel.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "itineraries")
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= DAY DETAILS =================
    private Integer dayNumber;

    private String location;

    private String activity;

    private String hotelName;

    @Column(length = 2000)
    private String notes;

    // ================= TRAVEL REQUEST =================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_request_id")
    @JsonIgnore
    private TravelRequest travelRequest;

    // ================= WORKFLOW FLAGS =================
    // itinerary is created
    private Boolean itineraryCreated = true;

    // travel started
    private Boolean travelStarted = false;

    // travel completed
    private Boolean completed = false;

    // ================= TIMESTAMP =================
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // ================= CONSTRUCTOR =================
    public Itinerary() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public TravelRequest getTravelRequest() {
        return travelRequest;
    }

    public void setTravelRequest(TravelRequest travelRequest) {
        this.travelRequest = travelRequest;
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
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
