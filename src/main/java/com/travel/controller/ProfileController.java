//package com.travel.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.travel.dto.ProfileResponse;
//import com.travel.dto.ProfileUpdateDTO;
//import com.travel.service.EmployeeService;
//
//@RestController
//@RequestMapping("/employee")
//@CrossOrigin("*")
//public class ProfileController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    // GET PROFILE
//    @GetMapping("/profile")
//    public ProfileResponse getProfile(
//            @RequestHeader("Authorization") String token
//    ) {
//        Long userId = employeeService.getUserIdFromToken(token);
//        return employeeService.getProfile(userId);
//    }
//
//    // UPDATE PROFILE
//    @PutMapping("/profile/update")
//    public String updateProfile(
//            @RequestHeader("Authorization") String token,
//            @RequestBody ProfileUpdateDTO dto
//    ) {
//        Long userId = employeeService.getUserIdFromToken(token);
//        return employeeService.updateProfile(userId, dto);
//    }
//}