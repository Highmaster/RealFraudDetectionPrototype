package com.example.frauddetectionproject.service.impl;

import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.model.TransactionDetails;
import com.example.frauddetectionproject.service.AlertGenerator;
import com.example.frauddetectionproject.service.HighTransactionAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HighTransactionAmountServiceImpl implements HighTransactionAmountService {

    private final AlertGenerator alertGenerator;

    @Override
    public List<AlertResponse> checkHighAmountTransactions(List<TransactionDetails> transactions, List<AlertResponse> alerts,
                                                   String userId) {
        double totalAmount = transactions.stream()
                .filter(event -> event.getTimestamp().isAfter(Instant.now().minusSeconds(24 * 60 * 60)))
                .mapToDouble(TransactionDetails::getAmount)
                .sum();

        long total24HoursTransaction = transactions.stream()
                .filter(event -> event.getTimestamp().isAfter(Instant.now().minusSeconds(24 * 60 * 60)))
                .toList().size();

        if (!transactions.isEmpty()) {
            double averageAmount = totalAmount / total24HoursTransaction;
            for(TransactionDetails event : transactions){
                if (event.getAmount() > (5 * averageAmount)) {
                    alerts.add(alertGenerator.generateHighTransactionAlert(userId));
                }
            }

        }
        return alerts;
    }
}
