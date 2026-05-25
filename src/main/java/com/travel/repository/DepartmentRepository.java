package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travel.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}