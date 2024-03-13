package com.example.frauddetectionproject.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlertName {

    HIGH_TRANSACTION("High Transaction Alert",
            "Transaction amount is 5x above the user's average in the last 24 hours"),

    MULTIPLE_SERVICE("Multiple Service Alert",
            "User conducting transaction in more than 3 distinct services within 5-minute window "),

    PING_PONG("Ping-Pong Activity Alert",
    "User's transactions bouncing back and forth between two services within 10-minute window");

    private final String alertName;
    private final String alertMessage;
}
