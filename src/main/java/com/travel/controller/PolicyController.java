package com.travel.controller;

import com.travel.entity.TravelPolicy;
import com.travel.repository.TravelPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private TravelPolicyRepository travelPolicyRepository;

    @GetMapping
    public List<TravelPolicy> getAllPolicies() {
        return travelPolicyRepository.findAll();
    }
}
