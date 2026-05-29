package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.travel.dto.ItineraryDTO;
import com.travel.entity.Itinerary;
import com.travel.service.ItineraryService;

@RestController
@RequestMapping("/api/itinerary")
@CrossOrigin("*")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    // ================= ADD ITINERARY =================
    // ONLY AFTER FINANCE APPROVAL
    @PostMapping("/add")
    public String addItinerary(@RequestBody ItineraryDTO dto) {
        return itineraryService.addItinerary(dto);
    }

    // ================= GET ITINERARY =================
    @GetMapping("/request/{requestId}")
    public List<Itinerary> getItinerary(@PathVariable Long requestId) {
        return itineraryService.getItinerary(requestId);
    }

    // ================= START TRAVEL =================
    @PutMapping("/start/{requestId}")
    public String startTravel(@PathVariable Long requestId) {
        return itineraryService.startTravel(requestId);
    }

    // ================= COMPLETE TRAVEL =================
    @PutMapping("/complete/{requestId}")
    public String completeTravel(@PathVariable Long requestId) {
        return itineraryService.completeTravel(requestId);
    }
}