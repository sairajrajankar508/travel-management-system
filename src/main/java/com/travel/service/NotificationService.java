package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.entity.Notification;
import com.travel.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getMyNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Transactional
    public void markRead(Long notificationId, Long userId) {
        Notification n = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        if (!n.getUser().getId().equals(userId)) {
            throw new RuntimeException("Forbidden");
        }
        n.setReadFlag(true);
        notificationRepository.save(n);
    }

    @Transactional
    public void delete(Long notificationId, Long userId) {
        Notification n = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        if (!n.getUser().getId().equals(userId)) {
            throw new RuntimeException("Forbidden");
        }
        notificationRepository.delete(n);
    }
}

