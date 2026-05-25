package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travel.entity.SystemConfig;

public interface SystemConfigRepository extends JpaRepository<SystemConfig, String> {
}