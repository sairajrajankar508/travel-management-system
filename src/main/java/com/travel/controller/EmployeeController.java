package com.travel.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.travel.dto.*;
import com.travel.enums.ExpenseCategory;
import com.travel.service.EmployeeService;
import com.travel.service.ExpenseService;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ExpenseService expenseService;

    // =====================================================
    // CREATE REQUEST
    // =====================================================
    @PostMapping("/request")
    public String createRequest(
            @RequestBody CreateTravelRequestDTO dto,
            @RequestHeader("Authorization") String token
    ) {
        return employeeService.createRequest(dto, token);
    }

    // =====================================================
    // GET MY REQUESTS
    // =====================================================
    @GetMapping("/requests")
    public List<TravelRequestResponse> getMyRequests(
            @RequestHeader("Authorization") String token
    ) {
        return employeeService.getMyRequests(token);
    }

    // =====================================================
    // DASHBOARD SUMMARY
    // =====================================================
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard(
            @RequestHeader("Authorization") String token
    ) {
        Long userId = employeeService.getUserIdFromToken(token);
        return employeeService.getDashboard(userId);
    }

    // =====================================================
    // SUBMIT REQUEST
    // =====================================================
    @PutMapping("/submit/{requestId}")
    public String submitRequest(
            @PathVariable Long requestId
    ) {
        return employeeService.submitRequest(requestId);
    }

    // =====================================================
    // CANCEL REQUEST (soft)
    // =====================================================
    @PutMapping("/cancel/{requestId}")
    public String cancelRequest(
            @PathVariable Long requestId,
            @RequestHeader("Authorization") String token
    ) {
        return employeeService.cancelRequest(requestId, token);
    }

    // =====================================================
    // DELETE REQUEST (hard)
    // =====================================================
    @DeleteMapping("/delete/{requestId}")
    public String deleteRequest(
            @PathVariable Long requestId,
            @RequestHeader("Authorization") String token
    ) {
        return employeeService.deleteRequest(requestId, token);
    }

    // =====================================================
    // ADD EXPENSE (WITH FILE UPLOAD)
    // =====================================================
    @PostMapping("/expense/add")
    public String addExpense(
            @RequestParam("title") String title,
            @RequestParam("amount") Double amount,
            @RequestParam("category") String category,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("expenseDate") String expenseDate,
            @RequestParam("travelRequestId") Long travelRequestId,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestHeader("Authorization") String token
    ) throws IOException {

        Long userId = employeeService.getUserIdFromToken(token);

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
    // GET MY EXPENSES
    // =====================================================
    @GetMapping("/expenses")
    public List<ExpenseResponse> getMyExpenses(
            @RequestHeader("Authorization") String token
    ) {
        Long userId = employeeService.getUserIdFromToken(token);
        return expenseService.getMyExpenses(userId);
    }

    // =====================================================
    // DELETE EXPENSE
    // =====================================================
    @DeleteMapping("/expense/{expenseId}")
    public String deleteExpense(
            @PathVariable Long expenseId,
            @RequestHeader("Authorization") String token
    ) {
        Long userId = employeeService.getUserIdFromToken(token);
        return expenseService.deleteExpense(expenseId, userId);
    }

    // =====================================================
    // PROFILE
    // =====================================================
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
