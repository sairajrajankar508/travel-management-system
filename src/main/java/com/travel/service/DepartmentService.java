package com.travel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.Department;
import com.travel.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // CREATE DEPARTMENT
    public Department create(Department dept) {
        dept.setActive(true);
        return departmentRepository.save(dept);
    }

    // GET ALL ACTIVE DEPARTMENTS ONLY (IMPROVED)
    public List<Department> getAll() {
        return departmentRepository.findAll()
                .stream()
                .filter(Department::isActive)
                .collect(Collectors.toList());
    }

    // UPDATE DEPARTMENT
    public Department update(Long id, String name) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        dept.setName(name);
        return departmentRepository.save(dept);
    }

    // DELETE (SOFT DELETE IMPROVED)
    public String delete(Long id) {

        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Soft delete instead of hard delete (recommended)
        dept.setActive(false);

        departmentRepository.save(dept);

        return "Department deactivated successfully";
    }
}
