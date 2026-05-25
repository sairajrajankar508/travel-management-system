//package com.travel.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.travel.entity.TravelRequest;
//import com.travel.entity.User;
//import com.travel.enums.RequestStatus;
//
//public interface TravelRequestRepository
//        extends JpaRepository<TravelRequest, Long> {
//
//    // FIND REQUESTS BY USER
//    List<TravelRequest> findByUser(User user);
//
//    // FIND REQUESTS BY STATUS
//    List<TravelRequest> findByStatus(
//            RequestStatus status
//    );
//}




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
    // FIND REQUESTS BY USER + STATUS
    // =====================================================

    List<TravelRequest> findByUserAndStatus(
            User user,
            RequestStatus status
    );

    // =====================================================
    // COUNT BY STATUS
    // =====================================================

    long countByStatus(
            RequestStatus status
    );

    // =====================================================
    // FIND ALL ACTIVE REQUESTS
    // =====================================================

    List<TravelRequest> findByStatusIn(
            List<RequestStatus> statuses
    );

}