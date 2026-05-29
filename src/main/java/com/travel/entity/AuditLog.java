package com.travel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    // =========================
    // PRIMARY KEY
    // =========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // ACTION PERFORMED
    // (CREATE / UPDATE / APPROVE / REJECT / DELETE)
    // =========================
    private String action;

    // =========================
    // WHO DID ACTION
    // =========================
    private String performedBy;

    // =========================
    // WHEN ACTION OCCURRED
    // =========================
    private LocalDateTime timestamp;

    // =========================
    // STATUS AFTER ACTION
    // =========================
    private String status;

    // =========================
    // OPTIONAL ENTITY INFO
    // =========================
    private Long entityId;

    private String entityType;

    // =========================
    // AUTO TIMESTAMP
    // =========================
    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}
