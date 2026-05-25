package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.travel.entity.Department;
import com.travel.service.DepartmentService;

@RestController
@RequestMapping("/admin/department")
@CrossOrigin("*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // CREATE DEPARTMENT
    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.create(department);
    }

    // GET ALL DEPARTMENTS
    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    // DELETE DEPARTMENT
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return departmentService.delete(id);
    }
}