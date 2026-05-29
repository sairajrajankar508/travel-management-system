package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.Expense;
import com.travel.entity.TravelRequest;
import com.travel.entity.User;
import com.travel.enums.ExpenseStatus;

public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {

    // =====================================================
    // USER EXPENSES
    // =====================================================

    List<Expense> findByUser(
            User user
    );

    // =====================================================
    // STATUS BASED
    // =====================================================

    List<Expense> findByStatus(
            ExpenseStatus status
    );

    long countByStatus(
            ExpenseStatus status
    );

    // =====================================================
    // TRAVEL REQUEST BASED
    // =====================================================

    List<Expense> findByTravelRequest(
            TravelRequest travelRequest
    );

    List<Expense> findTop10ByOrderByIdDesc();

}
