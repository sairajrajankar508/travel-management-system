package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.ApprovalHistory;
import com.travel.entity.TravelRequest;

public interface ApprovalHistoryRepository
        extends JpaRepository<ApprovalHistory, Long> {

    List<ApprovalHistory> findByTravelRequest(
            TravelRequest travelRequest
    );
}