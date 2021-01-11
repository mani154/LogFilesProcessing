package org.codejudge.sb.controller;

import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.controller.model.LogRequest;
import org.codejudge.sb.service.LogProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
@Slf4j
public class AppController {

    @Autowired
    LogProcessingService logProcessingService;

    @PostMapping("/api/process-logs/")
    public ResponseEntity processLogs(@RequestBody LogRequest logRequest) throws IOException {
        log.info("LogRequest is " + logRequest.toString());

        ErrorResponse errorResponse = logRequest.validate();
        if (errorResponse != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        return ResponseEntity.ok(logProcessingService.processLogs(
                logRequest.getLogFiles(),
                logRequest.getParallelFileProcessingCount()));
    }

}
