package com.example.frauddetectionproject;

import com.example.frauddetectionproject.domain.enums.AlertName;
import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.utils.DateUtils;
import com.example.frauddetectionproject.service.impl.AlertGeneratorImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AlertGeneratorImplTest {

    @Test
    public void testGenerateHighTransactionAlert() {
        String userId = "testUser";
        AlertGeneratorImpl alertGenerator = new AlertGeneratorImpl();

        AlertResponse alert = alertGenerator.generateHighTransactionAlert(userId);

        assertEquals(userId, alert.getUserId());
        assertEquals(AlertName.HIGH_TRANSACTION.getAlertName(), alert.getAlertName());
        assertEquals(AlertName.HIGH_TRANSACTION.getAlertMessage(), alert.getAlertMessage());
        assertEquals(DateUtils.getDateInStringformat(LocalDateTime.now()), alert.getAlertTime());
    }

    @Test
    public void testGenerateMultipleServiceAlert() {
        String userId = "testUser";
        AlertGeneratorImpl alertGenerator = new AlertGeneratorImpl();

        AlertResponse alert = alertGenerator.generateMultipleServiceAlert(userId);

        assertEquals(userId, alert.getUserId());
        assertEquals(AlertName.MULTIPLE_SERVICE.getAlertName(), alert.getAlertName());
        assertEquals(AlertName.MULTIPLE_SERVICE.getAlertMessage(), alert.getAlertMessage());
        assertEquals(DateUtils.getDateInStringformat(LocalDateTime.now()), alert.getAlertTime());
    }

    @Test
    public void testGeneratePingPongAlert() {
        String userId = "testUser";
        AlertGeneratorImpl alertGenerator = new AlertGeneratorImpl();


        AlertResponse alert = alertGenerator.generatePingPongAlert(userId);

        assertEquals(userId, alert.getUserId());
        assertEquals(AlertName.PING_PONG.getAlertName(), alert.getAlertName());
        assertEquals(AlertName.PING_PONG.getAlertMessage(), alert.getAlertMessage());
        assertEquals(DateUtils.getDateInStringformat(LocalDateTime.now()), alert.getAlertTime());
    }
}
