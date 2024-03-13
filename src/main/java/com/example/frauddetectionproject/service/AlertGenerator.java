package com.example.frauddetectionproject.service;

import com.example.frauddetectionproject.domain.model.AlertResponse;

import java.util.List;

public interface AlertGenerator {
    AlertResponse generateHighTransactionAlert(String userId);

    AlertResponse generateMultipleServiceAlert(String userId);

    AlertResponse generatePingPongAlert(String userId);
}
