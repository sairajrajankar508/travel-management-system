//package com.travel.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.travel.dto.CreateTravelRequestDTO;
//import com.travel.dto.ExpenseDTO;
//import com.travel.dto.ExpenseResponse;
//import com.travel.dto.TravelRequestResponse;
//import com.travel.enums.ExpenseCategory;
//import com.travel.service.EmployeeService;
//import com.travel.service.ExpenseService;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/employee")
//@CrossOrigin("*")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @Autowired
//    private ExpenseService expenseService;
//
//    // CREATE REQUEST
//    @PostMapping("/request")
//    public String createRequest(
//
//            @RequestBody
//            CreateTravelRequestDTO dto,
//
//            @RequestHeader("Authorization")
//            String token
//    ) {
//
//        return employeeService.createRequest(
//                dto,
//                token
//        );
//    }
//
//    // GET MY REQUESTS
//    @GetMapping("/requests")
//    public List<TravelRequestResponse> getMyRequests(
//
//            @RequestHeader("Authorization")
//            String token
//    ) {
//
//        return employeeService.getMyRequests(
//                token
//        );
//    }
//
//    // EDIT DRAFT REQUEST
//    @PutMapping("/edit/{requestId}")
//    public String editDraftRequest(
//
//            @PathVariable
//            Long requestId,
//
//            @RequestBody
//            CreateTravelRequestDTO dto,
//
//            @RequestHeader("Authorization")
//            String token
//    ) {
//
//        return employeeService.editDraftRequest(
//                requestId,
//                dto,
//                token
//        );
//    }
// 
//    // SUBMIT REQUEST
//    @PutMapping("/submit/{requestId}")
//    public String submitRequest(
//
//            @PathVariable
//            Long requestId
//    ) {
//
//        return employeeService.submitRequest(
//                requestId
//        );
//    }
//
//    // ADD EXPENSE WITH FILE UPLOAD
//    @PostMapping("/expense/add")
//    public String addExpense(
//
//            @RequestParam("title")
//            String title,
//
//            @RequestParam("amount")
//            Double amount,
//
//            @RequestParam("category")
//            String category,
//
//            @RequestParam("description")
//            String description,
//
//            @RequestParam("expenseDate")
//            String expenseDate,
//
//            @RequestParam("travelRequestId")
//            Long travelRequestId,
//
//            @RequestParam("file")
//            MultipartFile file,
//
//            @RequestHeader("Authorization")
//            String token
//
//    ) throws IOException {
//
//        Long userId =
//                employeeService.getUserIdFromToken(token);
//
//        ExpenseDTO dto = new ExpenseDTO();
//
//        dto.setTitle(title);
//
//        dto.setAmount(amount);
//
//        dto.setCategory(
//                ExpenseCategory.valueOf(category)
//        );
//
//        dto.setDescription(description);
//
//        dto.setExpenseDate(
//                java.time.LocalDate.parse(expenseDate)
//        );
//
//        dto.setTravelRequestId(travelRequestId);
//
//        return expenseService.addExpense(
//                userId,
//                dto,
//                file
//        );
//    }
//
//    // GET MY EXPENSES
//    @GetMapping("/expenses")
//    public List<ExpenseResponse> getMyExpenses(
//
//            @RequestHeader("Authorization")
//            String token
//    ) {
//
//        Long userId =
//                employeeService.getUserIdFromToken(token);
//
//        return expenseService.getMyExpenses(
//                userId
//        );
//    }
//
//    // DELETE REQUEST
//    @DeleteMapping("/delete/{requestId}")
//    public String deleteRequest(
//
//            @PathVariable
//            Long requestId,
//
//            @RequestHeader("Authorization")
//            String token
//    ) {
//
//        return employeeService.deleteRequest(
//                requestId,
//                token
//        );
//    }
//
// // DELETE EXPENSE
//    @DeleteMapping("/expense/{expenseId}")
//    public String deleteExpense(
//
//            @PathVariable
//            Long expenseId,
//
//            @RequestHeader("Authorization")
//            String token
//    ) {
//
//        Long userId =
//                employeeService.getUserIdFromToken(token);
//
//        return expenseService.deleteExpense(
//                expenseId,
//                userId
//        );
//    }
//  
//        
//}




package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.travel.dto.*;
import com.travel.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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
    // EDIT DRAFT REQUEST
    // =====================================================
    @PutMapping("/edit/{requestId}")
    public String editDraftRequest(
            @PathVariable Long requestId,
            @RequestBody CreateTravelRequestDTO dto,
            @RequestHeader("Authorization") String token
    ) {
        return employeeService.editDraftRequest(requestId, dto, token);
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
    // CANCEL REQUEST
    // =====================================================
    @PutMapping("/cancel/{requestId}")
    public String cancelRequest(
            @PathVariable Long requestId,
            @RequestHeader("Authorization") String token
    ) {
        return employeeService.cancelRequest(requestId, token);
    }

    // =====================================================
    // START TRAVEL
    // =====================================================
    @PutMapping("/start-travel/{requestId}")
    public String startTravel(
            @PathVariable Long requestId,
            @RequestHeader("Authorization") String token
    ) {
        return employeeService.startTravel(requestId, token);
    }

    // =====================================================
    // COMPLETE TRAVEL
    // =====================================================
    @PutMapping("/complete-travel/{requestId}")
    public String completeTravel(
            @PathVariable Long requestId,
            @RequestHeader("Authorization") String token
    ) {
        return employeeService.completeTravel(requestId, token);
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