//package com.travel.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.travel.entity.TravelRequest;
//import com.travel.repository.TravelRequestRepository;
//
//@RestController
//@RequestMapping("/admin/requests")
//@CrossOrigin("*")
//public class RequestController {
//
//    @Autowired
//    private TravelRequestRepository travelRequestRepository;
//
//    // GET ALL REQUESTS
//    @GetMapping
//    public List<TravelRequest> getAll() {
//        return travelRequestRepository.findAll();
//    }
//
//    // OVERRIDE STATUS (APPROVE / REJECT / PENDING)
//    @PutMapping("/override/{id}")
//    public String overrideStatus(
//            @PathVariable Long id,
//            @RequestParam String status
//    ) {
//
//        TravelRequest request = travelRequestRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Request not found"));
//
//   
//
//        travelRequestRepository.save(request);
//
//        return "Request updated to " + status;
//    }
//}



package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.travel.entity.TravelRequest;
import com.travel.enums.RequestStatus;
import com.travel.repository.TravelRequestRepository;

@RestController
@RequestMapping("/admin/requests")
@CrossOrigin("*")
public class RequestController {

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    // =====================================================
    // GET ALL REQUESTS
    // =====================================================
    @GetMapping
    public List<TravelRequest> getAll() {

        return travelRequestRepository.findAll();
    }

    // =====================================================
    // GET REQUEST BY STATUS
    // =====================================================
    @GetMapping("/status/{status}")
    public List<TravelRequest> getByStatus(

            @PathVariable
            RequestStatus status
    ) {

        return travelRequestRepository.findByStatus(
                status
        );
    }

    // =====================================================
    // ADMIN OVERRIDE STATUS
    // =====================================================
    @PutMapping("/override/{id}")
    public String overrideStatus(

            @PathVariable
            Long id,

            @RequestParam
            String status
    ) {

        TravelRequest request =
                travelRequestRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found"));

        try {

            RequestStatus newStatus =
                    RequestStatus.valueOf(
                            status.toUpperCase()
                    );

            request.setStatus(newStatus);

            travelRequestRepository.save(request);

            return "Request updated to " + newStatus;

        } catch (Exception e) {

            return "Invalid status value";
        }
    }

    // =====================================================
    // GET ACTIVE REQUESTS
    // =====================================================
    @GetMapping("/active")
    public List<TravelRequest> getActiveRequests() {

        return travelRequestRepository.findAll()
                .stream()
                .filter(r ->

                        r.getStatus() != RequestStatus.COMPLETED

                        &&

                        r.getStatus() != RequestStatus.REJECTED

                        &&

                        r.getStatus() != RequestStatus.CANCELLED
                )
                .toList();
    }

    // =====================================================
    // GET COMPLETED REQUESTS
    // =====================================================
    @GetMapping("/completed")
    public List<TravelRequest> getCompletedRequests() {

        return travelRequestRepository.findByStatus(
                RequestStatus.COMPLETED
        );
    }

}