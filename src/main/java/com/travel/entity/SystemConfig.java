//package com.travel.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//
//@Entity
//public class SystemConfig {
//
//    @Id
//    private String configKey;
//
//    private String configValue;
//
//	public String getConfigKey() {
//		return configKey;
//	}
//
//	public void setConfigKey(String configKey) {
//		this.configKey = configKey;
//	}
//
//	public String getConfigValue() {
//		return configValue;
//	}
//
//	public void setConfigValue(String configValue) {
//		this.configValue = configValue;
//	}    
//}



package com.travel.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_config")
public class SystemConfig {

    // =========================
    // CONFIG KEY (PRIMARY KEY)
    // =========================
    @Id
    @Column(name = "config_key", nullable = false)
    private String configKey;

    // =========================
    // CONFIG VALUE
    // =========================
    @Column(name = "config_value", length = 2000)
    private String configValue;

    // =========================
    // AUDIT FIELDS
    // =========================
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // =========================
    // CONSTRUCTOR
    // =========================
    public SystemConfig() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // =========================
    // AUTO UPDATE TIMESTAMP
    // =========================
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}