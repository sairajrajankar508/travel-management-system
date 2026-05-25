//package com.travel.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.travel.entity.Itinerary;
//import com.travel.entity.TravelRequest;
//
//public interface ItineraryRepository
//        extends JpaRepository<Itinerary, Long> {
//
//    List<Itinerary> findByTravelRequest(
//            TravelRequest travelRequest
//    );
//}



package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.entity.Itinerary;
import com.travel.entity.TravelRequest;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    // =====================================================
    // FIND BY TRAVEL REQUEST
    // =====================================================
    List<Itinerary> findByTravelRequest(TravelRequest travelRequest);

    // =====================================================
    // FIND ORDERED ITINERARY (DAY WISE)
    // =====================================================
    List<Itinerary> findByTravelRequestOrderByDayNumberAsc(TravelRequest travelRequest);

    // =====================================================
    // FIND BY REQUEST ID (OPTIONAL BUT USEFUL)
    // =====================================================
    List<Itinerary> findByTravelRequest_Id(Long travelRequestId);

    // =====================================================
    // DELETE ALL ITINERARY OF REQUEST
    // =====================================================
    void deleteByTravelRequest(TravelRequest travelRequest);
}