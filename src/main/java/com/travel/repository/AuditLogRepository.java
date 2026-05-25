package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.AuditLog;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {

}