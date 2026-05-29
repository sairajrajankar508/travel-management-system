package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.travel.entity.Department;
import com.travel.service.DepartmentService;

@RestController
@RequestMapping("/api/admin/department")
@CrossOrigin("*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // CREATE DEPARTMENT
    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.create(department));
    }

    // GET ALL DEPARTMENTS
    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    // UPDATE DEPARTMENT
    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(departmentService.update(id, body.get("name")));
    }

    // DELETE DEPARTMENT
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.delete(id));
    }
}