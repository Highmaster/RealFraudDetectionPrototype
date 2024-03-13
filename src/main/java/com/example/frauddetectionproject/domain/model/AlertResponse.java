package com.example.frauddetectionproject.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AlertResponse {

    private String userId;
    private String alertName;
    private String alertMessage;
    private String alertTime;
}
