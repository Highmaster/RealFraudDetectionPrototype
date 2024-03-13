package com.example.frauddetectionproject.service;

import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.model.TransactionDetails;

import java.util.List;

public interface PingPongActivityService {
    List<AlertResponse> checkPingPongActivity(List<TransactionDetails> transactions, List<AlertResponse> alerts,
                                      String userId);
}
