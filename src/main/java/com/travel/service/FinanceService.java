//package com.travel.service;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.travel.dto.ExpenseResponse;
//import com.travel.entity.Expense;
//import com.travel.entity.User;
//import com.travel.enums.ExpenseStatus;
//import com.travel.repository.ExpenseRepository;
//
//@Service
//public class FinanceService {
//
//    @Autowired
//    private ExpenseRepository expenseRepository;
//
//    // =====================================================
//    // FINANCE APPROVAL
//    // MANAGER_REVIEWED -> FINANCE_APPROVED
//    // =====================================================
//    public Map<String, Object> approveExpense(Long expenseId) {
//
//        Expense expense = expenseRepository.findById(expenseId)
//                .orElseThrow(() ->
//                        new RuntimeException("Expense not found"));
//
//        // SAFETY CHECK
//        if (expense.getStatus() != ExpenseStatus.MANAGER_REVIEWED) {
//
//            throw new RuntimeException(
//                    "Only manager reviewed expenses can be approved"
//            );
//        }
//
//        // STATUS CHANGE
//        expense.setStatus(
//                ExpenseStatus.FINANCE_APPROVED
//        );
//
//        expense.setApprovedBy("FINANCE");
//
//        expense.setActionDate(
//                LocalDateTime.now()
//        );
//
//        expenseRepository.save(expense);
//
//        Map<String, Object> response =
//                new HashMap<>();
//
//        response.put(
//                "message",
//                "Expense finance-approved successfully"
//        );
//
//        return response;
//    }
//
//    // =====================================================
//    // REJECT EXPENSE
//    // =====================================================
//    public Map<String, Object> rejectExpense(Long expenseId) {
//
//        Expense expense = expenseRepository.findById(expenseId)
//                .orElseThrow(() ->
//                        new RuntimeException("Expense not found"));
//
//        // ALREADY REIMBURSED CHECK
//        if (expense.getStatus() == ExpenseStatus.REIMBURSED) {
//
//            throw new RuntimeException(
//                    "Reimbursed expense cannot be rejected"
//            );
//        }
//
//        expense.setStatus(
//                ExpenseStatus.REJECTED
//        );
//
//        expense.setFinanceComment(
//                "Rejected by Finance"
//        );
//
//        expense.setActionDate(
//                LocalDateTime.now()
//        );
//
//        expenseRepository.save(expense);
//
//        Map<String, Object> response =
//                new HashMap<>();
//
//        response.put(
//                "message",
//                "Expense rejected successfully"
//        );
//
//        return response;
//    }
//
//    // =====================================================
//    // FINAL REIMBURSEMENT
//    // FINANCE_APPROVED -> REIMBURSED
//    // =====================================================
//    public Map<String, Object> reimburseExpense(Long expenseId) {
//
//        Expense expense = expenseRepository.findById(expenseId)
//                .orElseThrow(() ->
//                        new RuntimeException("Expense not found"));
//
//        // SAFETY CHECK
//        if (expense.getStatus() != ExpenseStatus.FINANCE_APPROVED) {
//
//            throw new RuntimeException(
//                    "Only finance approved expenses can be reimbursed"
//            );
//        }
//
//        // FINAL PAYMENT STATUS
//        expense.setStatus(
//                ExpenseStatus.REIMBURSED
//        );
//
//        expense.setActionDate(
//                LocalDateTime.now()
//        );
//
//        expenseRepository.save(expense);
//
//        Map<String, Object> response =
//                new HashMap<>();
//
//        response.put(
//                "message",
//                "Expense reimbursed successfully"
//        );
//
//        return response;
//    }
//
//    // =====================================================
//    // PENDING FINANCE APPROVALS
//    // SHOW IN ExpenseApproval.jsx
//    // =====================================================
//    public List<ExpenseResponse> getPendingApprovals() {
//
//        List<Expense> expenses =
//                expenseRepository.findByStatus(
//                        ExpenseStatus.MANAGER_REVIEWED
//                );
//
//        return expenses.stream().map(exp -> {
//
//            ExpenseResponse dto =
//                    new ExpenseResponse();
//
//            dto.setId(exp.getId());
//
//            dto.setTitle(exp.getTitle());
//
//            dto.setAmount(exp.getAmount());
//
//            dto.setCategory(
//                    exp.getCategory()
//            );
//
//            dto.setDescription(
//                    exp.getDescription()
//            );
//
//            dto.setEmployeeName(
//                    exp.getUser().getName()
//            );
//
//            dto.setStatus(
//                    exp.getStatus()
//            );
//
//            return dto;
//
//        }).toList();
//    }
//
//    // =====================================================
//    // PENDING REIMBURSEMENTS
//    // SHOW IN Reimbursements.jsx
//    // =====================================================
//    public List<ExpenseResponse> getPendingReimbursements() {
//
//        List<Expense> expenses =
//                expenseRepository.findByStatus(
//                        ExpenseStatus.FINANCE_APPROVED
//                );
//
//        return expenses.stream().map(exp -> {
//
//            ExpenseResponse dto =
//                    new ExpenseResponse();
//
//            dto.setId(exp.getId());
//
//            dto.setTitle(exp.getTitle());
//
//            dto.setAmount(exp.getAmount());
//
//            dto.setCategory(
//                    exp.getCategory()
//            );
//
//            dto.setDescription(
//                    exp.getDescription()
//            );
//
//            dto.setEmployeeName(
//                    exp.getUser().getName()
//            );
//
//            dto.setStatus(
//                    exp.getStatus()
//            );
//
//            return dto;
//
//        }).toList();
//    }
//
//    // =====================================================
//    // DEPARTMENT EXPENSE REPORT
//    // =====================================================
//    public Map<String, Double> departmentExpenseReport() {
//
//        List<Expense> expenses =
//                expenseRepository.findAll();
//
//        Map<String, Double> report =
//                new HashMap<>();
//
//        for (Expense expense : expenses) {
//
//            if (expense.getStatus()
//                    == ExpenseStatus.REIMBURSED) {
//
//                User user = expense.getUser();
//
//                String department =
//                        user.getDepartment();
//
//                report.put(
//                        department,
//
//                        report.getOrDefault(
//                                department,
//                                0.0
//                        ) + expense.getAmount()
//                );
//            }
//        }
//
//        return report;
//    }
//
//    // =====================================================
//    // FINANCE DASHBOARD
//    // =====================================================
//    public Map<String, Object> getDashboard() {
//
//        Map<String, Object> dashboard =
//                new HashMap<>();
//
//        long total =
//                expenseRepository.count();
//
//        long approved =
//                expenseRepository.countByStatus(
//                        ExpenseStatus.FINANCE_APPROVED
//                );
//
//        long reimbursed =
//                expenseRepository.countByStatus(
//                        ExpenseStatus.REIMBURSED
//                );
//
//        long rejected =
//                expenseRepository.countByStatus(
//                        ExpenseStatus.REJECTED
//                );
//
//        long pending =
//                expenseRepository.countByStatus(
//                        ExpenseStatus.MANAGER_REVIEWED
//                );
//
//        dashboard.put(
//                "totalExpenses",
//                total
//        );
//
//        dashboard.put(
//                "financeApproved",
//                approved
//        );
//
//        dashboard.put(
//                "reimbursedExpenses",
//                reimbursed
//        );
//
//        dashboard.put(
//                "rejectedExpenses",
//                rejected
//        );
//
//        dashboard.put(
//                "pendingApprovals",
//                pending
//        );
//
//        return dashboard;
//    }
//
//    public List<ExpenseResponse> getPaymentHistory() {
//
//        List<Expense> expenses = expenseRepository.findAll();
//
//        return expenses.stream()
//
//                .filter(exp ->
//
//                        exp.getStatus() == ExpenseStatus.REJECTED ||
//
//                        exp.getStatus() == ExpenseStatus.FINANCE_APPROVED ||
//
//                        exp.getStatus() == ExpenseStatus.REIMBURSED
//                )
//
//                .map(exp -> {
//
//                    ExpenseResponse dto = new ExpenseResponse();
//
//                    dto.setId(exp.getId());
//
//                    dto.setTitle(exp.getTitle());
//
//                    dto.setAmount(exp.getAmount());
//
//                    dto.setCategory(exp.getCategory());
//
//                    dto.setEmployeeName(
//                            exp.getUser().getName()
//                    );
//
//                    dto.setStatus(
//                            exp.getStatus()
//                    );
//
//                    dto.setFinanceComment(
//                            exp.getFinanceComment()
//                    );
//
//                    dto.setActionDate(
//                            exp.getActionDate()
//                    );
//
//                    return dto;
//
//                }).toList();
//    }
//
//    public Map<String, Double> getCategoryReport() {
//
//        List<Expense> expenses = expenseRepository.findAll();
//
//        Map<String, Double> report = new HashMap<>();
//
//        for (Expense expense : expenses) {
//
//            if (
//                expense.getStatus() == ExpenseStatus.FINANCE_APPROVED ||
//
//                expense.getStatus() == ExpenseStatus.REIMBURSED
//            ) {
//
//                String category =
//                        expense.getCategory().name();
//
//                report.put(
//                        category,
//                        report.getOrDefault(category, 0.0)
//                                + expense.getAmount()
//                );
//            }
//        }
//
//        return report;
//    }
//    
//    
//    public List<ExpenseResponse> getRecentExpenses() {
//
//        List<Expense> expenses =
//                expenseRepository.findTop10ByOrderByIdDesc();
//
//        return expenses.stream().map(exp -> {
//
//            ExpenseResponse dto = new ExpenseResponse();
//
//            dto.setId(exp.getId());
//
//            dto.setTitle(exp.getTitle());
//
//            dto.setAmount(exp.getAmount());
//
//            dto.setCategory(exp.getCategory());
//
//            dto.setStatus(exp.getStatus());
//
//            dto.setEmployeeName(
//                    exp.getUser().getName()
//            );
//
//            dto.setActionDate(
//                    exp.getActionDate()
//            );
//
//            return dto;
//
//        }).toList();
//    }
//    
//}



package com.travel.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dto.ExpenseResponse;
import com.travel.dto.TravelRequestResponse;
import com.travel.entity.*;
import com.travel.enums.*;
import com.travel.repository.*;

@Service
public class FinanceService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    // =====================================================
    // APPROVE EXPENSE
    // =====================================================
    public String approveExpense(Long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setStatus(ExpenseStatus.FINANCE_APPROVED);
        expense.setFinanceComment("Approved by Finance");
        expense.setActionDate(LocalDateTime.now());

        expenseRepository.save(expense);

        return "Expense approved successfully";
    }

    // =====================================================
    // REJECT EXPENSE
    // =====================================================
    public String rejectExpense(Long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setStatus(ExpenseStatus.REJECTED);
        expense.setFinanceComment("Rejected by Finance");
        expense.setActionDate(LocalDateTime.now());

        expenseRepository.save(expense);

        return "Expense rejected successfully";
    }

    // =====================================================
    // REIMBURSE EXPENSE
    // =====================================================
    public String reimburseExpense(Long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setStatus(ExpenseStatus.REIMBURSED);
        expense.setFinanceComment("Payment completed");
        expense.setActionDate(LocalDateTime.now());

        expenseRepository.save(expense);

        return "Expense reimbursed successfully";
    }

    // =====================================================
    // PENDING APPROVALS
    // =====================================================
    public List<ExpenseResponse> getPendingApprovals() {

        List<Expense> expenses =
                expenseRepository.findByStatus(ExpenseStatus.FINANCE_REVIEW);

        return expenses.stream().map(e -> {

            ExpenseResponse dto = new ExpenseResponse();

            dto.setId(e.getId());
            dto.setTitle(e.getTitle());
            dto.setAmount(e.getAmount());
            dto.setCategory(e.getCategory());
            dto.setDescription(e.getDescription());
            dto.setEmployeeName(e.getUser().getName());
            dto.setStatus(e.getStatus());

            return dto;

        }).collect(Collectors.toList());
    }

    // =====================================================
    // PENDING REIMBURSEMENTS
    // =====================================================
    public List<ExpenseResponse> getPendingReimbursements() {

        List<Expense> expenses =
                expenseRepository.findByStatus(ExpenseStatus.FINANCE_APPROVED);

        return expenses.stream().map(e -> {

            ExpenseResponse dto = new ExpenseResponse();

            dto.setId(e.getId());
            dto.setTitle(e.getTitle());
            dto.setAmount(e.getAmount());
            dto.setCategory(e.getCategory());
            dto.setDescription(e.getDescription());
            dto.setEmployeeName(e.getUser().getName());
            dto.setStatus(e.getStatus());

            return dto;

        }).collect(Collectors.toList());
    }

    // =====================================================
    // DASHBOARD
    // =====================================================
    public Map<String, Object> getDashboard() {

        Map<String, Object> map = new HashMap<>();

        map.put("totalExpenses", expenseRepository.count());
        map.put("pending", expenseRepository.countByStatus(ExpenseStatus.FINANCE_REVIEW));
        map.put("approved", expenseRepository.countByStatus(ExpenseStatus.FINANCE_APPROVED));
        map.put("reimbursed", expenseRepository.countByStatus(ExpenseStatus.REIMBURSED));
        map.put("rejected", expenseRepository.countByStatus(ExpenseStatus.REJECTED));

        return map;
    }

    // =====================================================
    // FINANCE REVIEW REQUESTS
    // =====================================================
    public List<TravelRequestResponse> getFinanceReviewRequests() {

        List<Expense> expenses =
                expenseRepository.findByStatus(ExpenseStatus.FINANCE_REVIEW);

        return expenses.stream().map(e -> {

            TravelRequest r = e.getTravelRequest();

            TravelRequestResponse dto = new TravelRequestResponse();

            dto.setId(r.getId());
            dto.setSource(r.getSource());
            dto.setDestination(r.getDestination());
            dto.setPurpose(r.getPurpose());
            dto.setStartDate(r.getStartDate());
            dto.setEndDate(r.getEndDate());
            dto.setBudget(r.getBudget());
            dto.setStatus(r.getStatus());
            dto.setEmployeeName(r.getUser().getName());

            return dto;

        }).collect(Collectors.toList());
    }

    // =====================================================
    // APPROVE TRAVEL REQUEST
    // =====================================================
    public String approveTravelRequest(Long requestId) {

        TravelRequest request = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(RequestStatus.FINANCE_APPROVED);

        travelRequestRepository.save(request);

        return "Travel request approved by finance";
    }

    // =====================================================
    // CATEGORY REPORT
    // =====================================================
    public Map<String, Double> getCategoryReport() {

        List<Expense> expenses = expenseRepository.findAll();

        Map<String, Double> map = new HashMap<>();

        for (Expense e : expenses) {

            if (e.getStatus() == ExpenseStatus.REIMBURSED
                    || e.getStatus() == ExpenseStatus.FINANCE_APPROVED) {

                String category = e.getCategory().name();

                map.put(category,
                        map.getOrDefault(category, 0.0) + e.getAmount());
            }
        }

        return map;
    }

    // =====================================================
    // RECENT EXPENSES
    // =====================================================
    public List<ExpenseResponse> getRecentExpenses() {

        return expenseRepository.findTop10ByOrderByIdDesc()
                .stream().map(e -> {

                    ExpenseResponse dto = new ExpenseResponse();

                    dto.setId(e.getId());
                    dto.setTitle(e.getTitle());
                    dto.setAmount(e.getAmount());
                    dto.setCategory(e.getCategory());
                    dto.setStatus(e.getStatus());
                    dto.setEmployeeName(e.getUser().getName());
                    dto.setActionDate(e.getActionDate());

                    return dto;

                }).collect(Collectors.toList());
    }

    // =====================================================
    // DEPARTMENT REPORT
    // =====================================================
    public Map<String, Double> departmentExpenseReport() {

        List<Expense> expenses = expenseRepository.findAll();

        Map<String, Double> map = new HashMap<>();

        for (Expense e : expenses) {

            if (e.getStatus() == ExpenseStatus.REIMBURSED) {

                String dept = e.getUser().getDepartment();

                map.put(dept,
                        map.getOrDefault(dept, 0.0) + e.getAmount());
            }
        }

        return map;
    }

    public String rejectTravelRequest(Long requestId) {

        TravelRequest request = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Travel request not found"));

        request.setStatus(RequestStatus.REJECTED);

        travelRequestRepository.save(request);

        return "Travel request rejected by finance";
    }
    
    public List<ExpenseResponse> getPaymentHistory() {

        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream()

                .filter(e ->
                        e.getStatus() == ExpenseStatus.REJECTED ||
                        e.getStatus() == ExpenseStatus.FINANCE_APPROVED ||
                        e.getStatus() == ExpenseStatus.REIMBURSED
                )

                .map(e -> {

                    ExpenseResponse dto = new ExpenseResponse();

                    dto.setId(e.getId());
                    dto.setTitle(e.getTitle());
                    dto.setAmount(e.getAmount());
                    dto.setCategory(e.getCategory());
                    dto.setStatus(e.getStatus());
                    dto.setEmployeeName(e.getUser().getName());
                    dto.setFinanceComment(e.getFinanceComment());
                    dto.setActionDate(e.getActionDate());

                    return dto;

                }).collect(Collectors.toList());
    }
    

}