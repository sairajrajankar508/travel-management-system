//package com.travel.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "approval_history")
//public class ApprovalHistory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String action;
//
//    private String comment;
//
//    private String approvedBy;
//
//    @ManyToOne
//    @JoinColumn(name = "travel_request_id")
//    private TravelRequest travelRequest;
//
//    // GETTERS & SETTERS
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getAction() {
//        return action;
//    }
//
//    public void setAction(String action) {
//        this.action = action;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public String getApprovedBy() {
//        return approvedBy;
//    }
//
//    public void setApprovedBy(String approvedBy) {
//        this.approvedBy = approvedBy;
//    }
//
//    public TravelRequest getTravelRequest() {
//        return travelRequest;
//    }
//
//    public void setTravelRequest(TravelRequest travelRequest) {
//        this.travelRequest = travelRequest;
//    }
//}



package com.travel.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "approval_history")
public class ApprovalHistory {

    // =========================
    // PRIMARY KEY
    // =========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // ACTION TYPE (APPROVED / REJECTED / SUBMITTED)
    // =========================
    private String action;

    // =========================
    // COMMENT FROM APPROVER
    // =========================
    @Column(length = 1000)
    private String comment;

    // =========================
    // APPROVER INFO
    // =========================
    private String approvedBy;

    // =========================
    // TIMESTAMP (IMPORTANT FOR AUDIT)
    // =========================
    private LocalDateTime actionTime;

    // =========================
    // RELATION WITH TRAVEL REQUEST
    // =========================
    @ManyToOne
    @JoinColumn(name = "travel_request_id")
    private TravelRequest travelRequest;

    // =========================
    // DEFAULT CONSTRUCTOR
    // =========================
    public ApprovalHistory() {
        this.actionTime = LocalDateTime.now();
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

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

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }

    public TravelRequest getTravelRequest() {
        return travelRequest;
    }

    public void setTravelRequest(TravelRequest travelRequest) {
        this.travelRequest = travelRequest;
    }
}