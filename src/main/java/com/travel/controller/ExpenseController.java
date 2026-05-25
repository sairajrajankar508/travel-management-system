//package com.travel.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.travel.dto.ExpenseResponse;
//import com.travel.service.ExpenseService;
//
//@RestController
//@RequestMapping("/expense")
//@CrossOrigin("*")
//public class ExpenseController {
//
//    @Autowired
//    private ExpenseService expenseService;
//
//    @GetMapping("/pending")
//    public List<ExpenseResponse> getPendingExpenses() {
//
//        return expenseService.getPendingExpenses();
//    }
//}



package com.travel.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.travel.dto.ExpenseDTO;
import com.travel.dto.ExpenseResponse;
import com.travel.enums.ExpenseCategory;
import com.travel.service.ExpenseService;

@RestController
@RequestMapping("/expense")
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // =====================================================
    // ADD EXPENSE (WITH FILE UPLOAD)
    // =====================================================
    @PostMapping("/add")
    public String addExpense(

            @RequestParam Long userId,

            @RequestParam String title,
            @RequestParam Double amount,
            @RequestParam String category,
            @RequestParam String description,
            @RequestParam String expenseDate,
            @RequestParam Long travelRequestId,

            @RequestParam(required = false) MultipartFile file

    ) throws IOException {

        ExpenseDTO dto = new ExpenseDTO();

        dto.setTitle(title);
        dto.setAmount(amount);
        dto.setCategory(ExpenseCategory.valueOf(category));
        dto.setDescription(description);
        dto.setExpenseDate(java.time.LocalDate.parse(expenseDate));
        dto.setTravelRequestId(travelRequestId);

        return expenseService.addExpense(userId, dto, file);
    }

    // =====================================================
    // SUBMIT EXPENSE
    // =====================================================
    @PutMapping("/submit/{expenseId}")
    public String submitExpense(

            @PathVariable Long expenseId,
            @RequestParam Long userId
    ) {
        return expenseService.submitExpense(expenseId, userId);
    }

    // =====================================================
    // PENDING EXPENSES
    // =====================================================
    @GetMapping("/pending")
    public List<ExpenseResponse> getPendingExpenses() {
        return expenseService.getPendingExpenses();
    }

    // =====================================================
    // MANAGER REVIEW
    // =====================================================
    @PutMapping("/review/{expenseId}")
    public String reviewExpense(
            @PathVariable Long expenseId
    ) {
        return expenseService.reviewExpense(expenseId);
    }

    // =====================================================
    // MOVE TO FINANCE
    // =====================================================
    @PutMapping("/complete/{expenseId}")
    public String completeExpenseWorkflow(
            @PathVariable Long expenseId
    ) {
        return expenseService.completeExpenseWorkflow(expenseId);
    }
}