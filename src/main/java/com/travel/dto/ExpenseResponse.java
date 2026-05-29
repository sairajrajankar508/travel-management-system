package com.travel.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.travel.enums.ExpenseCategory;
import com.travel.enums.ExpenseStatus;

public class ExpenseResponse {

    // =====================================================
    // BASIC DETAILS
    // =====================================================

    private Long id;

    private String title;

    private Double amount;

    private String description;

    private LocalDate expenseDate;

    // =====================================================
    // CATEGORY
    // =====================================================

    private ExpenseCategory category;

    // =====================================================
    // RECEIPT
    // =====================================================

    private String receiptUrl;

    // =====================================================
    // STATUS
    // =====================================================

    private ExpenseStatus status;

    // =====================================================
    // REQUEST LINK
    // =====================================================

    private Long travelRequestId;

    // =====================================================
    // EMPLOYEE DETAILS
    // =====================================================

    private String employeeName;

    // =====================================================
    // FINANCE DETAILS
    // =====================================================

    private String financeComment;

    private LocalDateTime actionDate;

    // =====================================================
    // GETTERS & SETTERS
    // =====================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }

    public Long getTravelRequestId() {
        return travelRequestId;
    }

    public void setTravelRequestId(Long travelRequestId) {
        this.travelRequestId = travelRequestId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getFinanceComment() {
        return financeComment;
    }

    public void setFinanceComment(String financeComment) {
        this.financeComment = financeComment;
    }

    public LocalDateTime getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDateTime actionDate) {
        this.actionDate = actionDate;
    }
}
