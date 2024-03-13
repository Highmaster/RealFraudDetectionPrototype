package com.example.frauddetectionproject.service.impl;

import com.example.frauddetectionproject.domain.enums.AlertName;
import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.utils.DateUtils;
import com.example.frauddetectionproject.service.AlertGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlertGeneratorImpl implements AlertGenerator {

    @Override
    public AlertResponse generateHighTransactionAlert(String userId){
        return AlertResponse.builder()
                .userId(userId)
                .alertName(AlertName.HIGH_TRANSACTION.getAlertName())
                .alertMessage(AlertName.HIGH_TRANSACTION.getAlertMessage())
                .alertTime(DateUtils.getDateInStringformat(LocalDateTime.now()))
                .build();
    }

    @Override
    public AlertResponse generateMultipleServiceAlert(String userId){
        return AlertResponse.builder()
                .userId(userId)
                .alertName(AlertName.MULTIPLE_SERVICE.getAlertName())
                .alertMessage(AlertName.MULTIPLE_SERVICE.getAlertMessage())
                .alertTime(DateUtils.getDateInStringformat(LocalDateTime.now()))
                .build();
    }

    @Override
    public AlertResponse generatePingPongAlert(String userId){
        return AlertResponse.builder()
                .userId(userId)
                .alertName(AlertName.PING_PONG.getAlertName())
                .alertMessage(AlertName.PING_PONG.getAlertMessage())
                .alertTime(DateUtils.getDateInStringformat(LocalDateTime.now()))
                .build();
    }
}
