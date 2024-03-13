package com.example.frauddetectionproject.service;

import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.model.TransactionDetails;

import java.util.List;

public interface FraudDetectorEngineService {

    List<AlertResponse> checkAllFraudActivities(List<TransactionDetails> events);

    List<AlertResponse> checkHighTransactionFraud(List<TransactionDetails> events);

    List<AlertResponse> checkPingPongFraud(List<TransactionDetails> events);

    List<AlertResponse> checkMultipleServiceFraud(List<TransactionDetails> events);



}
