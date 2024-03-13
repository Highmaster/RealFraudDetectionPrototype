package com.example.frauddetectionproject.domain.model;

import lombok.*;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionDetails {
    private Instant timestamp;
    private double amount;
    private String userID;
    private String serviceID;
}
