//package com.travel.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.travel.entity.Expense;
//import com.travel.entity.User;
//import com.travel.enums.ExpenseStatus;
//
//public interface ExpenseRepository extends JpaRepository<Expense, Long> {
//
//    List<Expense> findByUser(User user);
//
//    List<Expense> findByStatus(ExpenseStatus status);
//
//    long countByStatus(ExpenseStatus status);
//
//    List<Expense> findTop10ByOrderByIdDesc();
//    
//    // ================= OPTIONAL ANALYTICS =================
//
//    @Query("SELECT e.user.department, SUM(e.amount) FROM Expense e WHERE e.status = 'REIMBURSED' GROUP BY e.user.department")
//    List<Object[]> getDepartmentWiseExpense();
//
//
//}




package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    List<Expense> findByTravelRequestAndStatus(
            TravelRequest travelRequest,
            ExpenseStatus status
    );

    // =====================================================
    // RECENT EXPENSES
    // =====================================================

    List<Expense> findTop10ByOrderByIdDesc();

    // =====================================================
    // ANALYTICS
    // =====================================================

    @Query("""

        SELECT
            e.user.department,
            SUM(e.amount)

        FROM Expense e

        WHERE e.status = 'REIMBURSED'

        GROUP BY e.user.department

    """)
    List<Object[]> getDepartmentWiseExpense();

}