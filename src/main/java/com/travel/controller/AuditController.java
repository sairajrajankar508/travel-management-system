package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.entity.AuditLog;
import com.travel.repository.AuditLogRepository;

@RestController
public class AuditController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @GetMapping("/audit/logs")
    public List<AuditLog> getAuditLogs() {

        return auditLogRepository.findAll();
    }
}