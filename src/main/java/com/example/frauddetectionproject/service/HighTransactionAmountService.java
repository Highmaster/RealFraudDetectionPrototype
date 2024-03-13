package com.example.frauddetectionproject.service;

import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.model.TransactionDetails;

import java.util.List;

public interface HighTransactionAmountService {
    List<AlertResponse> checkHighAmountTransactions(List<TransactionDetails> transactions, List<AlertResponse> AlertResponses,
                                            String userId);
}
