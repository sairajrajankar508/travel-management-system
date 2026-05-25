//package com.travel.enums;
//
//public enum ExpenseStatus {
//
//    SUBMITTED,
//    MANAGER_REVIEWED,
//    FINANCE_APPROVED,
//    REIMBURSED,
//    REJECTED
//}


package com.travel.enums;

public enum ExpenseStatus {

    // EMPLOYEE
    SUBMITTED,

    // MANAGER
    MANAGER_REVIEW,

    // FINANCE
    FINANCE_REVIEW,
    FINANCE_APPROVED,

    // PAYMENT
    REIMBURSED,

    // FAILURE
    REJECTED
}