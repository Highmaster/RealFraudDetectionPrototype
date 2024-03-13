package com.example.frauddetectionproject.data;

import com.example.frauddetectionproject.domain.model.TransactionDetails;

import java.time.Instant;
import java.util.List;

public class DataFile {

    public static List<TransactionDetails> events( List<TransactionDetails> events){



        events.add(new TransactionDetails(Instant.now().plusSeconds(480), 150.00, "user1", "serviceA")); // User1 conducts a transaction in serviceA
        events.add(new TransactionDetails(Instant.now().plusSeconds(480), 200.00, "user1", "serviceB")); // User1 conducts a transaction in serviceB
        events.add(new TransactionDetails(Instant.now().plusSeconds(480), 250.00, "user1", "serviceC")); // User1 conducts a transaction in serviceC
        events.add(new TransactionDetails(Instant.now().plusSeconds(480), 300.00, "user1", "serviceD")); // User1 conducts a transaction in serviceD
        events.add(new TransactionDetails(Instant.now().plusSeconds(480), 350.00, "user1", "serviceE")); // User1 conducts a transaction in serviceE

        events.add(new TransactionDetails(Instant.now().plusSeconds(720), 400.00, "user1", "serviceF")); // User1 conducts a transaction in serviceF
        events.add(new TransactionDetails(Instant.now().plusSeconds(780), 450.00, "user1", "serviceG")); // User1 conducts a transaction in serviceG
        events.add(new TransactionDetails(Instant.now().plusSeconds(840), 500.00, "user1", "serviceF")); // User1 conducts another transaction in serviceF
        events.add(new TransactionDetails(Instant.now().plusSeconds(780), 450.00, "user1", "serviceG"));
        events.add(new TransactionDetails(Instant.now().plusSeconds(24 *60 *60 * 2), 10000.00, "user1", "serviceH")); // User1 conducts a transaction with an unusually high amount

        events.add(new TransactionDetails(Instant.now().plusSeconds(480), 150.00, "user2", "serviceA")); // User2 conducts a transaction in serviceA
        events.add(new TransactionDetails(Instant.now().plusSeconds(480), 200.00, "user2", "serviceB")); // User2 conducts a transaction in serviceB
        events.add(new TransactionDetails(Instant.now().plusSeconds(480), 250.00, "user2", "serviceC")); // User2 conducts a transaction in serviceC
        events.add(new TransactionDetails(Instant.now().plusSeconds(480), 300.00, "user2", "serviceD")); // User2 conducts a transaction in serviceD
        events.add(new TransactionDetails(Instant.now().plusSeconds(480), 350.00, "user2", "serviceE")); // User2 conducts a transaction in serviceE

        events.add(new TransactionDetails(Instant.now().plusSeconds(720), 400.00, "user2", "serviceF")); // User2 conducts a transaction in serviceF
        events.add(new TransactionDetails(Instant.now().plusSeconds(780), 450.00, "user2", "serviceG")); // User2 conducts a transaction in serviceG
        events.add(new TransactionDetails(Instant.now().plusSeconds(840), 500.00, "user2", "serviceF")); // User2 conducts another transaction in serviceF

        events.add(new TransactionDetails(Instant.now().plusSeconds(24 *60 *60 * 2), 10000.00, "user2", "serviceH")); // User2 conducts a transaction with an unusually high amount
return events;
    }
}
