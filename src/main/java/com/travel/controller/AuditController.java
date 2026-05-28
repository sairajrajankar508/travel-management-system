//package com.travel.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.travel.entity.AuditLog;
//import com.travel.repository.AuditLogRepository;
//
//@RestController
//public class AuditController {
//
//    @Autowired
//    private AuditLogRepository auditLogRepository;
//
//    @GetMapping("/audit/logs")
//    public List<AuditLog> getAuditLogs() {
//
//        return auditLogRepository.findAll();
//    }
//}



package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.entity.AuditLog;
import com.travel.repository.AuditLogRepository;

@RestController
@RequestMapping("/audit")
@CrossOrigin("*")
public class AuditController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // GET ALL AUDIT LOGS (LATEST FIRST OPTIONAL)
    @GetMapping("/logs")
    public ResponseEntity<List<AuditLog>> getAuditLogs() {

        List<AuditLog> logs = auditLogRepository.findAll();

        return ResponseEntity.ok(logs);
    }
}