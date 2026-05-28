package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.entity.Notification;
import com.travel.service.EmployeeService;
import com.travel.service.NotificationService;

@RestController
@RequestMapping("/api/employee/notifications")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getMyNotifications(@RequestHeader("Authorization") String token) {
        Long userId = employeeService.getUserIdFromToken(token);
        return notificationService.getMyNotifications(userId);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<String> markRead(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token
    ) {
        Long userId = employeeService.getUserIdFromToken(token);
        notificationService.markRead(id, userId);
        return ResponseEntity.ok("Marked as read");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token
    ) {
        Long userId = employeeService.getUserIdFromToken(token);
        notificationService.delete(id, userId);
        return ResponseEntity.ok("Deleted");
    }
}

