package com.example.frauddetectionproject.service;

import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.model.TransactionDetails;

import java.util.List;

public interface MultipleServiceTransaction {
    List<AlertResponse> checkMultipleServiceTransactions(List<TransactionDetails> transactions, List<AlertResponse> alerts,
                                                 String userId);
}
