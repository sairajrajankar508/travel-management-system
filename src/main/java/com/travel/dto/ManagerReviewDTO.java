////package com.travel.dto;
////
////public class ManagerReviewDTO {
////
////    private boolean approve;
////
////    private String comment;
////
////    public boolean isApprove() {
////        return approve;
////    }
////
////    public void setApprove(boolean approve) {
////        this.approve = approve;
////    }
////
////    public String getComment() {
////        return comment;
////    }
////
////    public void setComment(String comment) {
////        this.comment = comment;
////    }
////}
//
//
//
//
//package com.travel.dto;
//
//public class ManagerReviewDTO {
//
//    // =====================================================
//    // APPROVE / REJECT
//    // =====================================================
//
//    private boolean approve;
//
//    // =====================================================
//    // MANAGER COMMENT
//    // =====================================================
//
//    private String comment;
//
//    // =====================================================
//    // OPTIONAL APPROVED BUDGET
//    // =====================================================
//
//    private Double approvedBudget;
//
//    // =====================================================
//    // GETTERS & SETTERS
//    // =====================================================
//
//    public boolean isApprove() {
//        return approve;
//    }
//
//    public void setApprove(boolean approve) {
//        this.approve = approve;
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
//    public Double getApprovedBudget() {
//        return approvedBudget;
//    }
//
//    public void setApprovedBudget(Double approvedBudget) {
//        this.approvedBudget = approvedBudget;
//    }
//}



package com.travel.dto;

import jakarta.validation.constraints.NotNull;

public class ManagerReviewDTO {

    // =====================================================
    // APPROVE / REJECT
    // =====================================================
    @NotNull(message = "Approve field is required")
    private Boolean approve;

    // =====================================================
    // MANAGER COMMENT
    // =====================================================
    private String comment;

    // =====================================================
    // OPTIONAL APPROVED BUDGET
    // =====================================================
    private Double approvedBudget;

    // =====================================================
    // DEFAULT CONSTRUCTOR
    // =====================================================
    public ManagerReviewDTO() {
    }

    // =====================================================
    // GETTERS & SETTERS
    // =====================================================

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getApprovedBudget() {
        return approvedBudget;
    }

    public void setApprovedBudget(Double approvedBudget) {
        this.approvedBudget = approvedBudget;
    }
}