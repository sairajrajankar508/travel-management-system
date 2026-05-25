package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travel.entity.TravelPolicy;

public interface TravelPolicyRepository extends JpaRepository<TravelPolicy, Long> {
}