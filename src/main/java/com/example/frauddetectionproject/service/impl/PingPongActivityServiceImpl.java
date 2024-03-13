package com.example.frauddetectionproject.service.impl;

import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.model.TransactionDetails;
import com.example.frauddetectionproject.service.AlertGenerator;
import com.example.frauddetectionproject.service.PingPongActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PingPongActivityServiceImpl implements PingPongActivityService {

    private final AlertGenerator AlertResponseGenerator;

    @Override
    public List<AlertResponse> checkPingPongActivity(List<TransactionDetails> transactions, List<AlertResponse> AlertResponses,
                                             String userId) {

        Map<String, TransactionDetails> last2ServiceMap = new LinkedHashMap<>();
        TransactionDetails firstTransaction = transactions.get(0);

        last2ServiceMap.put(firstTransaction.getServiceID(), firstTransaction);

        int index = 0;
        for (int i = 1; i < transactions.size(); i++) {
            TransactionDetails nextTransaction = transactions.get(i);
            if (!firstTransaction.getServiceID().equals(nextTransaction.getServiceID())
                    && Duration.between(firstTransaction.getTimestamp(),
                    nextTransaction.getTimestamp()).getSeconds() <= (10 * 60)) {
                last2ServiceMap.put(nextTransaction.getServiceID(), nextTransaction);
            }
            if (last2ServiceMap.size() == 2) {
                index = transactions.lastIndexOf(nextTransaction);
                break;
            }
        }

        for (int j = index + 2; j < transactions.size() - 1; j++) {
            TransactionDetails currentTransaction = transactions.get(j);
            TransactionDetails previousTransaction = transactions.get(j - 1);

            Iterator<Map.Entry<String, TransactionDetails>> iterator = last2ServiceMap.entrySet().iterator();
            Map.Entry<String, TransactionDetails> firstEntry = iterator.next();
            Map.Entry<String, TransactionDetails> secondEntry = iterator.next();

            firstTransaction = firstEntry.getValue();
            TransactionDetails secondTransaction = secondEntry.getValue();

            Duration duration = Duration.between(firstTransaction.getTimestamp(), currentTransaction.getTimestamp());

            if (firstTransaction.getServiceID().equals(previousTransaction.getServiceID()) &&
                    secondTransaction.getServiceID().equals(currentTransaction.getServiceID()) &&
                    duration.getSeconds() <= (10 * 60)) {
                AlertResponses.add(AlertResponseGenerator.generatePingPongAlert(userId));
                last2ServiceMap.clear();
                last2ServiceMap.put(previousTransaction.getServiceID(), previousTransaction);
                last2ServiceMap.put(currentTransaction.getServiceID(), currentTransaction);
            } else if (!secondTransaction.getServiceID().equals(previousTransaction.getServiceID())) {
                String removedService = firstTransaction.getServiceID();
                last2ServiceMap.remove(removedService);
                last2ServiceMap.put(previousTransaction.getServiceID(), previousTransaction);
            }
        }
        return AlertResponses;
    }

}
