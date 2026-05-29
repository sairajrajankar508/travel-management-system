package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.entity.Itinerary;
import com.travel.entity.TravelRequest;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    List<Itinerary> findByTravelRequestOrderByDayNumberAsc(TravelRequest travelRequest);
}
