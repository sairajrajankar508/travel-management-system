//package com.travel.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.travel.dto.TravelRequestResponse;
//import com.travel.entity.TravelRequest;
//import com.travel.entity.TravelPolicy;
//import com.travel.repository.TravelPolicyRepository;
//import com.travel.service.ManagerService;
//
//@RestController
//@RequestMapping("/manager")
//@CrossOrigin("*")
//public class ManagerController {
//
//    @Autowired
//    private ManagerService managerService;
//
//    @Autowired
//    private TravelPolicyRepository travelPolicyRepository;
//
//    // PENDING REQUESTS
//    @GetMapping("/requests")
//    public List<TravelRequestResponse> getPendingRequests() {
//        return managerService.getPendingRequests();
//    }
//
//    // REVIEW REQUEST
//    @PutMapping("/review/{id}")
//    public String reviewRequest(
//            @PathVariable Long id,
//            @RequestParam boolean approve,
//            @RequestParam String comment
//    ) {
//        return managerService.reviewRequest(id, approve, comment);
//    }
//
//    // APPROVAL HISTORY
//    @GetMapping("/history")
//    public List<TravelRequest> getApprovalHistory() {
//        return managerService.getApprovalHistory();
//    }
//
//    // TEAM ACTIVITY
//    @GetMapping("/team-activity")
//    public List<TravelRequestResponse> getTeamActivities() {
//        return managerService.getTeamActivities();
//    }
//
//    // POLICIES (READ ONLY)
//    @GetMapping("/policies")
//    public List<TravelPolicy> getPolicies() {
//        return travelPolicyRepository.findAll();
//    }
//
//    // REPORTS
//    @GetMapping("/reports/total")
//    public long totalRequests() {
//        return managerService.getTotalRequests();
//    }
//
//    @GetMapping("/reports/approved")
//    public long approvedRequests() {
//        return managerService.getApprovedRequests();
//    }
//
//    @GetMapping("/reports/rejected")
//    public long rejectedRequests() {
//        return managerService.getRejectedRequests();
//    }
//
//    @GetMapping("/reports/budget")
//    public double totalBudget() {
//        return managerService.getTotalBudget();
//    }
//}




package com.travel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.travel.dto.ProfileResponse;
import com.travel.dto.ProfileUpdateDTO;
import com.travel.dto.TravelRequestResponse;
import com.travel.entity.TravelRequest;
import com.travel.entity.TravelPolicy;
import com.travel.repository.TravelPolicyRepository;
import com.travel.service.EmployeeService;
import com.travel.service.ManagerService;

@RestController
@RequestMapping("/api/manager")
@CrossOrigin("*")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TravelPolicyRepository travelPolicyRepository;

    // ================= DASHBOARD SUMMARY =================
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {

        Map<String, Object> res = new HashMap<>();

        long total = managerService.getTotalRequests();
        long approved = managerService.getApprovedRequests();
        long rejected = managerService.getRejectedRequests();
        double totalBudget = managerService.getTotalBudget();
        int pending = managerService.getPendingRequests().size();

        res.put("totalRequests", total);
        res.put("pendingRequests", pending);
        res.put("approvedRequests", approved);
        res.put("rejectedRequests", rejected);
        res.put("totalBudget", totalBudget);

        return res;
    }

    // ================= PENDING REQUESTS =================
    @GetMapping("/requests")
    public List<TravelRequestResponse> getPendingRequests() {

        return managerService.getPendingRequests();
    }

    // ================= REVIEW REQUEST =================
    @PutMapping("/review/{id}")
    public String reviewRequest(

            @PathVariable
            Long id,

            @RequestParam
            boolean approve,

            @RequestParam
            String comment
    ) {

        return managerService.reviewRequest(
                id,
                approve,
                comment
        );
    }

    // ================= APPROVAL HISTORY =================
    @GetMapping("/history")
    public List<TravelRequest> getApprovalHistory() {

        return managerService.getApprovalHistory();
    }

    // ================= TEAM ACTIVITY =================
    @GetMapping("/team-activity")
    public List<TravelRequestResponse> getTeamActivities() {

        return managerService.getTeamActivities();
    }

    // ================= POLICIES =================
    @GetMapping("/policies")
    public List<TravelPolicy> getPolicies() {

        return travelPolicyRepository.findAll();
    }

    // ================= REPORTS =================
    @GetMapping("/reports/total")
    public long totalRequests() {

        return managerService.getTotalRequests();
    }

    @GetMapping("/reports/approved")
    public long approvedRequests() {

        return managerService.getApprovedRequests();
    }

    @GetMapping("/reports/rejected")
    public long rejectedRequests() {

        return managerService.getRejectedRequests();
    }

    @GetMapping("/reports/budget")
    public double totalBudget() {

        return managerService.getTotalBudget();
    }

    // ================= PROFILE =================
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
