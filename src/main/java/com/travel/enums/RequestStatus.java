//package com.travel.enums;
//
//public enum RequestStatus {
//
//    DRAFT,
//    SUBMITTED,
//    MANAGER_APPROVED,
//    REJECTED,
//    FINANCE_APPROVED,
//    COMPLETED
//}


package com.travel.enums;

public enum RequestStatus {

    // EMPLOYEE
    DRAFT,
    SUBMITTED,

    // MANAGER
    MANAGER_REVIEW,
    MANAGER_APPROVED,

    // FINANCE
    FINANCE_REVIEW,
    FINANCE_APPROVED,

    // ITINERARY
    ITINERARY_CREATED,

    // TRAVEL
    TRAVEL_IN_PROGRESS,

    // EXPENSE
    EXPENSE_SUBMITTED,
    EXPENSE_REVIEW,

    // PAYMENT
    REIMBURSED,

    // FINAL
    COMPLETED,

    // FAILURE STATES
    CANCELLED,
    REJECTED,
    DISMISSED
}
