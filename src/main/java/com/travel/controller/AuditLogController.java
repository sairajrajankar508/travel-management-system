package com.travel.controller;

import com.travel.entity.AuditLog;
import com.travel.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/audit")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuditLogController {

    private final AuditLogService auditLogService;

    // Admin panel endpoint (ADMIN role): GET /api/admin/audit
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllLogs() {

        List<AuditLog> logs = auditLogService.getAllLogs();

        return ResponseEntity.ok(logs);
    }

    // Note: frontend should use GET /api/admin/audit
}
