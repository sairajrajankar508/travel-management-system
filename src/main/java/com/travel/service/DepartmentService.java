package com.travel.service;

import java.util.List;

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

    // GET ALL
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    // DELETE
    public String delete(Long id) {

        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRepository.delete(dept);

        return "Department deleted successfully";
    }
}