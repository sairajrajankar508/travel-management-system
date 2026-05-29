package com.travel.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.travel.dto.ProfileResponse;
import com.travel.dto.ProfileUpdateDTO;
import com.travel.dto.ExpenseResponse;
import com.travel.dto.TravelRequestResponse;
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

    @GetMapping("/monthly-report")
    public Map<String, Double> monthlyReport() {
        return financeService.getMonthlyExpenseReport();
    }

    @GetMapping("/monthly-requests-report")
    public Map<String, Long> monthlyRequestsReport() {
        return financeService.getMonthlyRequestReport();
    }

    @GetMapping("/recent-expenses")
    public List<ExpenseResponse> recentExpenses() {
        return financeService.getRecentExpenses();
    }

    // ===================== PENDING APPROVALS =====================

    @GetMapping("/pending-approvals")
    public List<ExpenseResponse> getPendingApprovals() {
        return financeService.getPendingApprovals();
    }

    // ===================== PENDING REIMBURSEMENTS =====================

    @GetMapping("/pending-reimbursements")
    public List<ExpenseResponse> getPendingReimbursements() {
        return financeService.getPendingReimbursements();
    }

    // ===================== PAYMENT HISTORY =====================
    
    @PutMapping("/travel-reject/{requestId}")
    public String rejectTravelRequest(
            @PathVariable Long requestId,
            @RequestBody(required = false) Map<String, String> body
    ) {
        String comment = (body != null) ? body.get("comment") : null;
        return financeService.rejectTravelRequest(requestId, comment);
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

}
