package com.travel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "approval_history")
public class ApprovalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;

    private String comment;

    private String approvedBy;

    @ManyToOne
    @JoinColumn(name = "travel_request_id")
    private TravelRequest travelRequest;

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public TravelRequest getTravelRequest() {
        return travelRequest;
    }

    public void setTravelRequest(TravelRequest travelRequest) {
        this.travelRequest = travelRequest;
    }
}