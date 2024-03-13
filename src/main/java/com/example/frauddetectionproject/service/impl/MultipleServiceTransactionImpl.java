package com.example.frauddetectionproject.service.impl;


import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.model.TransactionDetails;
import com.example.frauddetectionproject.service.AlertGenerator;
import com.example.frauddetectionproject.service.MultipleServiceTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class MultipleServiceTransactionImpl implements MultipleServiceTransaction {

    private final AlertGenerator alertGenerator;

    @Override
    public List<AlertResponse> checkMultipleServiceTransactions(List<TransactionDetails> transactions, List<AlertResponse> alerts,
                                                                String userId) {

        Map<String, String> distintServiceMap = new HashMap<>();

        TransactionDetails firstTransaction = transactions.get(0);
        distintServiceMap.put(firstTransaction.getServiceID(), firstTransaction.getTimestamp().toString());
        for(int i = 1; i < transactions.size(); i++){
            TransactionDetails nextTransaction = transactions.get(i);
            if(!firstTransaction.getServiceID().equals(nextTransaction.getServiceID()) &&
                    Duration.between(firstTransaction.getTimestamp(),
                            nextTransaction.getTimestamp()).getSeconds() <= (5*60)){
                distintServiceMap.put(nextTransaction.getServiceID(), nextTransaction.getTimestamp().toString());
            }
            if(!firstTransaction.getServiceID().equals(nextTransaction.getServiceID()) &&
                    Duration.between(firstTransaction.getTimestamp(),
                            nextTransaction.getTimestamp()).getSeconds() > (5*60)){
                distintServiceMap.remove(firstTransaction.getServiceID());
                firstTransaction = nextTransaction;
                distintServiceMap.put(firstTransaction.getServiceID(), firstTransaction.getTimestamp().toString());
            }
            if(distintServiceMap.size() > 3){
               alerts.add(alertGenerator.generateMultipleServiceAlert(userId));
            }
        }
        return alerts;
    }
}
