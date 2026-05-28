package com.travel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.entity.AuditLog;
import com.travel.service.AuditLogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/audit")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ApiAuditController {

    private final AuditLogService auditLogService;

    @GetMapping("/logs")
    public ResponseEntity<List<AuditLog>> getAuditLogs() {
        return ResponseEntity.ok(auditLogService.getAllLogs());
    }
}

