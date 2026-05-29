package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.TravelRequest;
import com.travel.entity.User;
import com.travel.enums.RequestStatus;

public interface TravelRequestRepository
        extends JpaRepository<TravelRequest, Long> {

    // =====================================================
    // FIND REQUESTS BY USER
    // =====================================================

    List<TravelRequest> findByUser(
            User user
    );

    // =====================================================
    // FIND REQUESTS BY STATUS
    // =====================================================

    List<TravelRequest> findByStatus(
            RequestStatus status
    );

    // =====================================================
    // FIND POLICY VIOLATED REQUESTS
    // =====================================================

    List<TravelRequest> findByPolicyViolatedTrue();

}
