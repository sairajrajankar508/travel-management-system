package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.WorkflowConfig;

public interface WorkflowConfigRepository extends JpaRepository<WorkflowConfig, Long> {

    Optional<WorkflowConfig> findByConfigKey(String configKey);
}
