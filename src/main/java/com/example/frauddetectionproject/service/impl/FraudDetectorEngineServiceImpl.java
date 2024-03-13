package com.example.frauddetectionproject.service.impl;

import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.model.FraudDetectorEngine;
import com.example.frauddetectionproject.domain.model.TransactionDetails;
import com.example.frauddetectionproject.service.FraudDetectorEngineService;
import com.example.frauddetectionproject.service.HighTransactionAmountService;
import com.example.frauddetectionproject.service.MultipleServiceTransaction;
import com.example.frauddetectionproject.service.PingPongActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FraudDetectorEngineServiceImpl implements FraudDetectorEngineService {

    private final FraudDetectorEngine fraudDetectorEngine = new FraudDetectorEngine();

    private final HighTransactionAmountService highTransactionAmountService;
    private final MultipleServiceTransaction multipleServiceTransaction;
    private final PingPongActivityService pingPongActivityService;
    @Override
    public List<AlertResponse> checkAllFraudActivities(List<TransactionDetails> events) {
        Set<String> distinctUserIDs = new HashSet<>();
        List<AlertResponse> AlertResponses = fraudDetectorEngine.getAlerts();
        for (TransactionDetails event : events) {
            distinctUserIDs.add(event.getUserID());
        }
        for (String userID : distinctUserIDs) {
            List<TransactionDetails> transactions = fraudDetectorEngine.getUserTransactions()
                    .computeIfAbsent(userID, k -> new ArrayList<>());
            List<TransactionDetails> userEvents = events.stream()
                    .filter(event -> event.getUserID().equals(userID))
                    .toList();

            transactions.addAll(userEvents);

            highTransactionAmountService.checkHighAmountTransactions(userEvents, AlertResponses, userID);
            pingPongActivityService.checkPingPongActivity(userEvents, AlertResponses, userID);
            multipleServiceTransaction.checkMultipleServiceTransactions(userEvents, AlertResponses, userID);
        }

        return AlertResponses;
    }

    @Override
    public List<AlertResponse> checkHighTransactionFraud(List<TransactionDetails> events) {
        Set<String> distinctUserIDs = new HashSet<>();
        List<AlertResponse> AlertResponses = fraudDetectorEngine.getAlerts();
        for (TransactionDetails event : events) {
            distinctUserIDs.add(event.getUserID());
        }
        for (String userID : distinctUserIDs) {
            List<TransactionDetails> transactions = fraudDetectorEngine.getUserTransactions()
                    .computeIfAbsent(userID, k -> new ArrayList<>());
            List<TransactionDetails> userEvents = events.stream()
                    .filter(event -> event.getUserID().equals(userID))
                    .toList();

            transactions.addAll(userEvents);

            AlertResponses.addAll(highTransactionAmountService.checkHighAmountTransactions(userEvents, AlertResponses, userID));
        }

        return AlertResponses;
    }

    @Override
    public List<AlertResponse> checkPingPongFraud(List<TransactionDetails> events) {
        Set<String> distinctUserIDs = new HashSet<>();
        List<AlertResponse> AlertResponses = fraudDetectorEngine.getAlerts();
        for (TransactionDetails event : events) {
            distinctUserIDs.add(event.getUserID());
        }
        for (String userID : distinctUserIDs) {
            List<TransactionDetails> transactions = fraudDetectorEngine.getUserTransactions()
                    .computeIfAbsent(userID, k -> new ArrayList<>());
            List<TransactionDetails> userEvents = events.stream()
                    .filter(event -> event.getUserID().equals(userID))
                    .toList();

            transactions.addAll(userEvents);

            AlertResponses.addAll(pingPongActivityService.checkPingPongActivity(userEvents, AlertResponses, userID));
        }

        return AlertResponses;
    }

    @Override
    public List<AlertResponse> checkMultipleServiceFraud(List<TransactionDetails> events) {
        Set<String> distinctUserIDs = new HashSet<>();
        List<AlertResponse> AlertResponses = fraudDetectorEngine.getAlerts();
        for (TransactionDetails event : events) {
            distinctUserIDs.add(event.getUserID());
        }
        for (String userID : distinctUserIDs) {
            List<TransactionDetails> transactions = fraudDetectorEngine.getUserTransactions()
                    .computeIfAbsent(userID, k -> new ArrayList<>());
            List<TransactionDetails> userEvents = events.stream()
                    .filter(event -> event.getUserID().equals(userID))
                    .toList();

            transactions.addAll(userEvents);

            AlertResponses.addAll(multipleServiceTransaction.checkMultipleServiceTransactions(userEvents, AlertResponses, userID));
        }

        return AlertResponses;
    }

}
