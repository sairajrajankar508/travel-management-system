package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.ApprovalHistory;
import com.travel.entity.TravelRequest;
import com.travel.repository.ApprovalHistoryRepository;
import com.travel.repository.TravelRequestRepository;

@Service
public class ApprovalHistoryService {

    @Autowired
    private ApprovalHistoryRepository historyRepository;

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    // SAVE HISTORY
    public void saveHistory(
            TravelRequest request,
            String action,
            String comment,
            String approvedBy
    ) {

        ApprovalHistory history = new ApprovalHistory();

        history.setAction(action);
        history.setComment(comment);
        history.setApprovedBy(approvedBy);

        history.setTravelRequest(request);

        historyRepository.save(history);
    }

    // GET HISTORY
    public List<ApprovalHistory> getHistory(
            Long requestId
    ) {

        TravelRequest request = travelRequestRepository
                .findById(requestId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Travel request not found"
                        ));

        return historyRepository.findByTravelRequest(request);
    }
}