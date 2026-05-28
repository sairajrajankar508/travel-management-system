//package com.travel.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.travel.dto.ExpenseResponse;
//import com.travel.service.FinanceService;
//
//@RestController
//@RequestMapping("/finance")
//@CrossOrigin("*")
//public class FinanceController {
//
//    @Autowired
//    private FinanceService financeService;
//
//    // =====================================================
//    // FINANCE APPROVAL
//    // MANAGER_REVIEWED -> FINANCE_APPROVED
//    // =====================================================
//    @PutMapping("/approve/{expenseId}")
//    public Map<String, Object> approveExpense(
//
//            @PathVariable
//            Long expenseId
//    ) {
//
//        return financeService.approveExpense(
//                expenseId
//        );
//    }
//
//    // =====================================================
//    // REJECT EXPENSE
//    // =====================================================
//    @PutMapping("/reject/{expenseId}")
//    public Map<String, Object> rejectExpense(
//
//            @PathVariable
//            Long expenseId
//    ) {
//
//        return financeService.rejectExpense(
//                expenseId
//        );
//    }
//
//    // =====================================================
//    // FINAL REIMBURSEMENT
//    // FINANCE_APPROVED -> REIMBURSED
//    // =====================================================
//    @PutMapping("/reimburse/{expenseId}")
//    public Map<String, Object> reimburseExpense(
//
//            @PathVariable
//            Long expenseId
//    ) {
//
//        return financeService.reimburseExpense(
//                expenseId
//        );
//    }
//
//    // =====================================================
//    // PENDING FINANCE APPROVALS
//    // SHOW IN ExpenseApproval.jsx
//    // =====================================================
//    @GetMapping("/pending-approvals")
//    public List<ExpenseResponse> getPendingApprovals() {
//
//        return financeService.getPendingApprovals();
//    }
//
//    // =====================================================
//    // PENDING REIMBURSEMENTS
//    // SHOW IN Reimbursements.jsx
//    // =====================================================
//    @GetMapping("/pending-reimbursements")
//    public List<ExpenseResponse> getPendingReimbursements() {
//
//        return financeService.getPendingReimbursements();
//    }
//
//    // =====================================================
//    // DEPARTMENT EXPENSE REPORT
//    // =====================================================
//    @GetMapping("/report")
//    public Map<String, Double> departmentExpenseReport() {
//
//        return financeService.departmentExpenseReport();
//    }
//
//    // =====================================================
//    // FINANCE DASHBOARD
//    // =====================================================
//    @GetMapping("/dashboard")
//    public Map<String, Object> dashboard() {
//
//        return financeService.getDashboard();
//    }
//
//    @GetMapping("/payment-history")
//    public List<ExpenseResponse> getPaymentHistory() {
//
//        return financeService.getPaymentHistory();
//    }
//
//    @GetMapping("/category-report")
//    public Map<String, Double> categoryReport() {
//
//        return financeService.getCategoryReport();
//    }
//    
//    @GetMapping("/recent-expenses")
//    public List<ExpenseResponse> recentExpenses() {
//
//        return financeService.getRecentExpenses();
//    }
//    
//}



package com.travel.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.travel.dto.ProfileResponse;
import com.travel.dto.ProfileUpdateDTO;
import com.travel.dto.ExpenseResponse;
import com.travel.dto.TravelRequestResponse;
import com.travel.entity.AuditLog;
import com.travel.repository.AuditLogRepository;
import com.travel.service.EmployeeService;
import com.travel.service.FinanceService;

@RestController
@RequestMapping("/api/finance")
@CrossOrigin("*")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuditLogRepository auditLogRepository;

    // ===================== TRAVEL REQUESTS =====================

    @GetMapping("/travel-requests")
    public List<TravelRequestResponse> getFinanceReviewRequests() {
        return financeService.getFinanceReviewRequests();
    }

    @PutMapping("/travel-approve/{requestId}")
    public String approveTravelRequest(@PathVariable Long requestId) {
        return financeService.approveTravelRequest(requestId);
    }

    // ❌ FIX: removed rejectTravelRequest (not in service)
    // agar reject chahiye to expense reject use karo

    // ===================== EXPENSE WORKFLOW =====================

    @PutMapping("/approve/{expenseId}")
    public String approveExpense(@PathVariable Long expenseId) {
        return financeService.approveExpense(expenseId);
    }

    @PutMapping("/reject/{expenseId}")
    public String rejectExpense(@PathVariable Long expenseId) {
        return financeService.rejectExpense(expenseId);
    }

    @PutMapping("/reimburse/{expenseId}")
    public String reimburseExpense(@PathVariable Long expenseId) {
        return financeService.reimburseExpense(expenseId);
    }

    // ===================== DASHBOARD =====================

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        return financeService.getDashboard();
    }

    // ===================== REPORTS =====================

    @GetMapping("/report")
    public Map<String, Double> departmentExpenseReport() {
        return financeService.departmentExpenseReport();
    }

    @GetMapping("/category-report")
    public Map<String, Double> categoryReport() {
        return financeService.getCategoryReport();
    }

    @GetMapping("/recent-expenses")
    public List<ExpenseResponse> recentExpenses() {
        return financeService.getRecentExpenses();
    }

    // ===================== PAYMENT HISTORY =====================
    // ❌ FIX: removed getPaymentHistory (not in service)
    
    @PutMapping("/travel-reject/{requestId}")
    public String rejectTravelRequest(@PathVariable Long requestId) {
        return financeService.rejectTravelRequest(requestId);
    }

    @GetMapping("/payment-history")
    public List<ExpenseResponse> getPaymentHistory() {
        return financeService.getPaymentHistory();
    }

    // ===================== PROFILE =====================
    @GetMapping("/profile")
    public ProfileResponse getProfile(
            @RequestHeader("Authorization") String token
    ) {
        Long userId = employeeService.getUserIdFromToken(token);
        return employeeService.getProfile(userId);
    }

    @PutMapping("/profile/update")
    public String updateProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody ProfileUpdateDTO dto
    ) {
        Long userId = employeeService.getUserIdFromToken(token);
        return employeeService.updateProfile(userId, dto);
    }

    // ===================== AUDIT & VERIFICATION =====================
    @GetMapping("/audit/logs")
    public List<AuditLog> getAuditLogs() {
        return auditLogRepository.findAll();
    }
    
}
