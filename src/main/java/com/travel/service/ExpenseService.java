//package com.travel.service;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.travel.dto.ExpenseDTO;
//import com.travel.dto.ExpenseResponse;
//import com.travel.entity.Expense;
//import com.travel.entity.TravelRequest;
//import com.travel.entity.User;
//import com.travel.enums.ExpenseStatus;
//import com.travel.repository.ExpenseRepository;
//import com.travel.repository.TravelRequestRepository;
//import com.travel.repository.UserRepository;
//
//@Service
//public class ExpenseService {
//
//    @Autowired
//    private ExpenseRepository expenseRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private TravelRequestRepository travelRequestRepository;
//
//    // ===== FILE UPLOAD DIR (SAFE & FIXED) =====
//    private static final String UPLOAD_DIR =
//            System.getProperty("user.dir")
//                    + File.separator
//                    + "file_uploads"
//                    + File.separator;
//
//    // ================= ADD EXPENSE =================
//    public String addExpense(
//            Long userId,
//            ExpenseDTO dto,
//            MultipartFile file
//    ) throws IOException {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        TravelRequest request = travelRequestRepository.findById(dto.getTravelRequestId())
//                .orElseThrow(() -> new RuntimeException("Travel Request not found"));
//
//        // CREATE DIRECTORY IF NOT EXISTS
//        File dir = new File(UPLOAD_DIR);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        // FILE SAVE
//        String fileName = null;
//
//        if (file != null && !file.isEmpty()) {
//
//            fileName =
//                    System.currentTimeMillis()
//                            + "_"
//                            + file.getOriginalFilename();
//
//            File destination = new File(UPLOAD_DIR + fileName);
//
//            file.transferTo(destination);
//        }
//
//        // CREATE EXPENSE
//        Expense expense = new Expense();
//
//        expense.setTitle(dto.getTitle());
//        expense.setAmount(dto.getAmount());
//        expense.setDescription(dto.getDescription());
//        expense.setExpenseDate(dto.getExpenseDate());
//        expense.setCategory(dto.getCategory());
//
//        expense.setStatus(ExpenseStatus.MANAGER_REVIEWED);
//        
//        
//        expense.setUser(user);
//        expense.setTravelRequest(request);
//
//        // SAVE FILE NAME IN DB (NOT URL)
//        expense.setReceiptUrl(fileName);
//
//        expenseRepository.save(expense);
//
//        return "Expense added successfully";
//    }
//
//    // ================= GET EXPENSES =================
//    public List<ExpenseResponse> getMyExpenses(Long userId) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow();
//
//        return expenseRepository.findByUser(user)
//                .stream()
//                .map(exp -> {
//                    ExpenseResponse res = new ExpenseResponse();
//
//                    res.setId(exp.getId());
//
//                    res.setTitle(exp.getTitle());
//
//                    res.setAmount(exp.getAmount());
//
//                    res.setCategory(
//                            exp.getCategory()
//                    );
//
//                    res.setDescription(exp.getDescription());
//
//                    res.setExpenseDate(exp.getExpenseDate());
//
//                    res.setTravelRequestId(
//                            exp.getTravelRequest().getId()
//                    );
//
//                    res.setStatus(exp.getStatus());
//
//                    // IMPORTANT: FILE NAME RETURN
//                    res.setReceiptUrl(exp.getReceiptUrl());
//
//                    return res;
//                })
//                .collect(Collectors.toList());
//    }
//
//    // ================= DELETE EXPENSE =================
//    public String deleteExpense(Long expenseId, Long userId) {
//
//        Expense expense = expenseRepository.findById(expenseId)
//                .orElseThrow(() -> new RuntimeException("Expense not found"));
//
//        if (!expense.getUser().getId().equals(userId)) {
//            return "Unauthorized";
//        }
//
//        expenseRepository.delete(expense);
//
//        return "Expense deleted successfully";
//    }
//
//    public List<ExpenseResponse> getPendingExpenses() {
//
//        List<Expense> expenses =
//                expenseRepository.findByStatus(ExpenseStatus.MANAGER_REVIEWED);
//
//        return expenses.stream().map(exp -> {
//
//            ExpenseResponse dto = new ExpenseResponse();
//
//            dto.setId(exp.getId());
//            dto.setTitle(exp.getTitle());
//            dto.setAmount(exp.getAmount());
//            dto.setCategory(exp.getCategory());
//            dto.setDescription(exp.getDescription());
//            dto.setEmployeeName(exp.getUser().getName());
//
//            return dto;
//
//        }).toList();
//    }
//
//}




package com.travel.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travel.dto.ExpenseDTO;
import com.travel.dto.ExpenseResponse;
import com.travel.entity.Expense;
import com.travel.entity.TravelRequest;
import com.travel.entity.User;
import com.travel.enums.ExpenseStatus;
import com.travel.enums.RequestStatus;
import com.travel.repository.ExpenseRepository;
import com.travel.repository.TravelRequestRepository;
import com.travel.repository.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    private static final String UPLOAD_DIR =
            System.getProperty("user.dir") + File.separator + "file_uploads" + File.separator;

    // =====================================================
    // ADD EXPENSE
    // =====================================================
    public String addExpense(Long userId, ExpenseDTO dto, MultipartFile file) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TravelRequest request = travelRequestRepository.findById(dto.getTravelRequestId())
                .orElseThrow(() -> new RuntimeException("Travel Request not found"));

        if (request.getStatus() != RequestStatus.TRAVEL_IN_PROGRESS) {
            return "Expenses allowed only after travel starts";
        }

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        String fileName = null;

        if (file != null && !file.isEmpty()) {
            fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            file.transferTo(new File(UPLOAD_DIR + fileName));
        }

        Expense expense = new Expense();

        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setCategory(dto.getCategory());

        expense.setStatus(ExpenseStatus.SUBMITTED);

        expense.setUser(user);
        expense.setTravelRequest(request);
        expense.setReceiptUrl(fileName);
        expense.setActionDate(LocalDateTime.now());

        expenseRepository.save(expense);

        request.setStatus(RequestStatus.EXPENSE_SUBMITTED);
        travelRequestRepository.save(request);

        return "Expense added successfully";
    }

    // =====================================================
    // SUBMIT EXPENSE
    // =====================================================
    public String submitExpense(Long expenseId, Long userId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!expense.getUser().getId().equals(userId)) {
            return "Unauthorized";
        }

        expense.setStatus(ExpenseStatus.SUBMITTED);

        expenseRepository.save(expense);

        return "Expense submitted";
    }

    // =====================================================
    // MANAGER REVIEW
    // =====================================================
    public String reviewExpense(Long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (expense.getStatus() != ExpenseStatus.SUBMITTED) {
            return "Only submitted expenses allowed";
        }

        expense.setStatus(ExpenseStatus.MANAGER_REVIEW);

        expenseRepository.save(expense);

        return "Reviewed by Manager";
    }

    // =====================================================
    // MOVE TO FINANCE
    // =====================================================
    public String completeExpenseWorkflow(Long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (expense.getStatus() != ExpenseStatus.MANAGER_REVIEW) {
            return "Must be in manager review stage";
        }

        expense.setStatus(ExpenseStatus.FINANCE_REVIEW);

        expenseRepository.save(expense);

        return "Moved to Finance";
    }

    // =====================================================
    // PENDING EXPENSES
    // =====================================================
    public List<ExpenseResponse> getPendingExpenses() {

        return expenseRepository.findByStatus(ExpenseStatus.SUBMITTED)
                .stream()
                .map(e -> {

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
}