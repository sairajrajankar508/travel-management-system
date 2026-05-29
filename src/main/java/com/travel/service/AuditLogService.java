package com.travel.service;

import com.travel.entity.AuditLog;
import com.travel.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    // =====================================================
    // SAVE AUDIT LOG
    // =====================================================
    public void saveLog(
            String action,
            String performedBy,
            String status
    ) {

        AuditLog log = AuditLog.builder()
                .action(action)
                .performedBy(performedBy)
                .status(status)
                .timestamp(LocalDateTime.now())
                .build();

        auditLogRepository.save(log);
    }

    // =====================================================
    // GET ALL LOGS
    // =====================================================
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
}
