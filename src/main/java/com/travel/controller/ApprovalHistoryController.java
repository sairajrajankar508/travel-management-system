//package com.travel.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.travel.entity.ApprovalHistory;
//import com.travel.service.ApprovalHistoryService;
//
//@RestController
//@RequestMapping("/history")
//public class ApprovalHistoryController {
//
//    @Autowired
//    private ApprovalHistoryService historyService;
//
//    // GET APPROVAL HISTORY
//    @GetMapping("/{requestId}")
//    public List<ApprovalHistory> getHistory(
//            @PathVariable Long requestId
//    ) {
//        return historyService.getHistory(requestId);
//    }
//}



package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.entity.ApprovalHistory;
import com.travel.service.ApprovalHistoryService;

@RestController
@RequestMapping("/history")
@CrossOrigin("*")
public class ApprovalHistoryController {

    @Autowired
    private ApprovalHistoryService historyService;

    // GET APPROVAL HISTORY
    @GetMapping("/{requestId}")
    public ResponseEntity<List<ApprovalHistory>> getHistory(
            @PathVariable Long requestId
    ) {
        return ResponseEntity.ok(
                historyService.getHistory(requestId)
        );
    }
}