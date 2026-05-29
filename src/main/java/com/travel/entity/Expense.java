package com.travel.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.travel.enums.ExpenseCategory;
import com.travel.enums.ExpenseStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= BASIC =================

    private String title;

    private Double amount;

    private String description;

    private LocalDate expenseDate;

    // ================= CATEGORY =================

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    // ================= STATUS =================

    @Enumerated(EnumType.STRING)
    private ExpenseStatus status;

    // ================= RECEIPT =================

    private String receiptUrl;

    // ================= FINANCE =================

    private String approvedBy;

    private String financeComment;

    private LocalDateTime actionDate;

    // ================= RELATIONS =================

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "travel_request_id")
    private TravelRequest travelRequest;

    // ================= GETTERS & SETTERS =================

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

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TravelRequest getTravelRequest() {
        return travelRequest;
    }

    public void setTravelRequest(TravelRequest travelRequest) {
        this.travelRequest = travelRequest;
    }
}
