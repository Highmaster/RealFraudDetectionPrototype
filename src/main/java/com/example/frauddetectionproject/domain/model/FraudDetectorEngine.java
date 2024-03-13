package com.example.frauddetectionproject.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AllArgsConstructor
@Data
public class FraudDetectorEngine {
    private final Map<String, List<TransactionDetails>> userTransactions = new HashMap<>();
    private final List<AlertResponse> alerts = new ArrayList<>();
}
