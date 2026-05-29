package com.travel.dto;

import java.time.LocalDate;

import com.travel.enums.ExpenseCategory;

public class ExpenseDTO {

    // =====================================================
    // BASIC DETAILS
    // =====================================================

    private String title;

    private Double amount;

    private ExpenseCategory category;

    private String description;

    // =====================================================
    // EXPENSE DATE
    // =====================================================

    private LocalDate expenseDate;

    // =====================================================
    // TRAVEL REQUEST LINK
    // =====================================================

    private Long travelRequestId;

    // =====================================================
    // GETTERS & SETTERS
    // =====================================================

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

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
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

    public Long getTravelRequestId() {
        return travelRequestId;
    }

    public void setTravelRequestId(Long travelRequestId) {
        this.travelRequestId = travelRequestId;
    }
}
