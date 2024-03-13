package com.example.frauddetectionproject.controller;

import com.example.frauddetectionproject.data.DataFile;
import com.example.frauddetectionproject.domain.model.AlertResponse;
import com.example.frauddetectionproject.domain.model.TransactionDetails;
import com.example.frauddetectionproject.domain.utils.CSVExporter;
import com.example.frauddetectionproject.service.FraudDetectorEngineService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/fraud")
@RequiredArgsConstructor
public class FraudDetectionController {

    private final FraudDetectorEngineService fraudDetectorEngineService;
    private final String CSV_FILE_PATH = "csvfile.csv";

    private List<TransactionDetails> generateTransactionEventsForFraud() {
        List<TransactionDetails> events = new ArrayList<>();
        return  DataFile.events(events);
    }

    @GetMapping("/all-fraud")
    public List<AlertResponse> checkFraudTransactions(){
        return fraudDetectorEngineService.checkAllFraudActivities(generateTransactionEventsForFraud());
    }

    @GetMapping("/download-csv")
    public ResponseEntity<Void> exportCsv (HttpServletResponse httpServletResponse) throws FileNotFoundException {
        List<TransactionDetails> events = new ArrayList<>();
        CSVExporter.exportToCSVHttpResponse(DataFile.events(events),httpServletResponse,CSV_FILE_PATH);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
