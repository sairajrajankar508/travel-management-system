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

        TravelRequest request = expense.getTravelRequest();

        if (request != null) {
            request.setStatus(RequestStatus.REIMBURSED);
            request.setReimbursed(true);
            travelRequestRepository.save(request);
        }

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
    // PENDING REIMBURSEMENTS (expenses submitted by employee)
    // =====================================================
    public List<ExpenseResponse> getPendingReimbursements() {

        List<Expense> expenses =
                expenseRepository.findByStatus(ExpenseStatus.SUBMITTED);

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
    // FINANCE REVIEW REQUESTS (Travel Requests pending finance approval)
    // =====================================================
    public List<TravelRequestResponse> getFinanceReviewRequests() {

        List<TravelRequest> requests =
                travelRequestRepository.findByStatus(RequestStatus.FINANCE_REVIEW);

        return requests.stream().map(r -> {

            TravelRequestResponse dto = new TravelRequestResponse();

            dto.setId(r.getId());
            dto.setSource(r.getSource());
            dto.setDestination(r.getDestination());
            dto.setPurpose(r.getPurpose());
            dto.setStartDate(r.getStartDate());
            dto.setEndDate(r.getEndDate());
            dto.setBudget(r.getBudget());
            dto.setTransportMode(r.getTransportMode());
            dto.setAccommodation(r.getAccommodation());
            dto.setStatus(r.getStatus());
            dto.setDocumentUrl(r.getDocumentUrl());
            dto.setPriority(r.getPriority() != null ? r.getPriority().name() : null);
            dto.setPolicyViolated(r.getPolicyViolated());
            dto.setPolicyViolationReason(r.getPolicyViolationReason());
            dto.setManagerComment(r.getManagerComment());
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

    // =====================================================
    // MONTHLY EXPENSE CHART DATA
    // =====================================================
    public Map<String, Double> getMonthlyExpenseReport() {

        List<Expense> expenses = expenseRepository.findAll();

        Map<String, Double> map = new LinkedHashMap<>();

        for (Expense e : expenses) {

            if (e.getExpenseDate() != null) {

                String month = e.getExpenseDate().getMonth().toString()
                        + " " + e.getExpenseDate().getYear();

                map.put(month,
                        map.getOrDefault(month, 0.0) + e.getAmount());
            }
        }

        return map;
    }

    // =====================================================
    // MONTHLY REQUEST CHART DATA
    // =====================================================
    public Map<String, Long> getMonthlyRequestReport() {

        List<TravelRequest> requests = travelRequestRepository.findAll();

        Map<String, Long> map = new LinkedHashMap<>();

        for (TravelRequest r : requests) {

            if (r.getCreatedAt() != null) {

                String month = r.getCreatedAt().getMonth().toString()
                        + " " + r.getCreatedAt().getYear();

                map.put(month,
                        map.getOrDefault(month, 0L) + 1);
            }
        }

        return map;
    }

    public String rejectTravelRequest(Long requestId, String comment) {

        TravelRequest request = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Travel request not found"));

        request.setStatus(RequestStatus.REJECTED);
        request.setFinanceComment(comment);

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
