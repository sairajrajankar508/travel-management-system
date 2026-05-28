//package com.travel.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.travel.entity.TravelPolicy;
//import com.travel.repository.TravelPolicyRepository;
//
//@Service
//public class PolicyService {
//
//    @Autowired
//    private TravelPolicyRepository policyRepository;
//
//    @Autowired
//    private AuditLogService auditLogService;
//
//    // CREATE POLICY
//    public String createPolicy(
//            Double maxBudget,
//            String allowedClass
//    ) {
//
//        TravelPolicy policy = new TravelPolicy();
//
//        policy.setMaxBudget(maxBudget);
//
//        policy.setAllowedClass(allowedClass);
//
//        policy.setActive(true);
//
//        policyRepository.save(policy);
//
//        auditLogService.saveLog(
//                "POLICY_CREATED",
//                "ADMIN",
//                "SUCCESS"
//        );
//
//        return "Policy created successfully";
//    }
//
//    // GET ALL
//    public List<TravelPolicy> getAllPolicies() {
//        return policyRepository.findAll();
//    }
//
//    // TOGGLE STATUS
//    public String togglePolicy(Long id) {
//
//        TravelPolicy policy =
//                policyRepository.findById(id)
//                        .orElseThrow(() ->
//                                new RuntimeException("Policy not found")
//                        );
//
//        policy.setActive(!policy.isActive());
//
//        policyRepository.save(policy);
//
//        auditLogService.saveLog(
//                "POLICY_STATUS_UPDATED",
//                "ADMIN",
//                "SUCCESS"
//        );
//
//        return policy.isActive()
//                ? "Policy Enabled"
//                : "Policy Disabled";
//    }
//
//    // DELETE
//    public String deletePolicy(Long id) {
//
//        TravelPolicy policy =
//                policyRepository.findById(id)
//                        .orElseThrow(() ->
//                                new RuntimeException("Policy not found")
//                        );
//
//        policyRepository.delete(policy);
//
//        auditLogService.saveLog(
//                "POLICY_DELETED",
//                "ADMIN",
//                "SUCCESS"
//        );
//
//        return "Policy deleted";
//    }
//}



package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.TravelPolicy;
import com.travel.repository.TravelPolicyRepository;

@Service
public class PolicyService {

    @Autowired
    private TravelPolicyRepository policyRepository;

    @Autowired
    private AuditLogService auditLogService;

    // CREATE POLICY
    public String createPolicy(Double maxBudget, String allowedClass) {

        TravelPolicy policy = new TravelPolicy();
        policy.setMaxBudget(maxBudget);
        policy.setAllowedClass(allowedClass);
        policy.setActive(true);

        policyRepository.save(policy);

        auditLogService.saveLog(
                "POLICY_CREATED",
                "ADMIN",
                "SUCCESS"
        );

        return "Policy created successfully";
    }

    // GET ALL POLICIES
    public List<TravelPolicy> getAllPolicies() {
        return policyRepository.findAll();
    }

    // TOGGLE POLICY STATUS
    public String togglePolicy(Long id) {

        TravelPolicy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setActive(!policy.isActive());
        policyRepository.save(policy);

        auditLogService.saveLog(
                "POLICY_STATUS_UPDATED",
                "ADMIN",
                "SUCCESS"
        );

        return policy.isActive()
                ? "Policy Enabled"
                : "Policy Disabled";
    }

    // DELETE POLICY
    public String deletePolicy(Long id) {

        TravelPolicy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policyRepository.delete(policy);

        auditLogService.saveLog(
                "POLICY_DELETED",
                "ADMIN",
                "SUCCESS"
        );

        return "Policy deleted";
    }
} 