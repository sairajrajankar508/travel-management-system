package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dto.ItineraryDTO;
import com.travel.entity.Itinerary;
import com.travel.entity.TravelRequest;
import com.travel.enums.RequestStatus;
import com.travel.repository.ItineraryRepository;
import com.travel.repository.TravelRequestRepository;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    // =====================================================
    // ADD ITINERARY (ONLY FINANCE APPROVED)
    // =====================================================
    public String addItinerary(ItineraryDTO dto) {

        TravelRequest request = travelRequestRepository.findById(dto.getTravelRequestId())
                .orElseThrow(() -> new RuntimeException("Travel request not found"));

        if (request.getStatus() != RequestStatus.FINANCE_APPROVED) {
            return "❌ Itinerary allowed only after Finance Approval";
        }

        Itinerary itinerary = new Itinerary();

        itinerary.setDayNumber(dto.getDayNumber());
        itinerary.setLocation(dto.getLocation());
        itinerary.setActivity(dto.getActivity());
        itinerary.setHotelName(dto.getHotelName());
        itinerary.setNotes(dto.getNotes());
        itinerary.setTravelRequest(request);

        itineraryRepository.save(itinerary);

        // STATUS UPDATE
        request.setStatus(RequestStatus.ITINERARY_CREATED);
        travelRequestRepository.save(request);

        return "Itinerary created successfully";
    }

    // =====================================================
    // GET ITINERARY (ORDERED)
    // =====================================================
    public List<Itinerary> getItinerary(Long requestId) {

        TravelRequest request = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        return itineraryRepository.findByTravelRequestOrderByDayNumberAsc(request);
    }

    // =====================================================
    // START TRAVEL (ONLY AFTER ITINERARY)
    // =====================================================
    public String startTravel(Long requestId) {

        TravelRequest request = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RequestStatus.ITINERARY_CREATED) {
            return "❌ Travel cannot start before itinerary creation";
        }

        request.setStatus(RequestStatus.TRAVEL_IN_PROGRESS);
        travelRequestRepository.save(request);

        return "Travel started successfully";
    }

    // =====================================================
    // COMPLETE TRAVEL
    // =====================================================
    public String completeTravel(Long requestId) {

        TravelRequest request = travelRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RequestStatus.TRAVEL_IN_PROGRESS) {
            return "Travel is not in progress";
        }

        request.setStatus(RequestStatus.COMPLETED);
        travelRequestRepository.save(request);

        return "Travel completed successfully";
    }
}
