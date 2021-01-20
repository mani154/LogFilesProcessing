package org.codejudge.sb.controller;

import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.controller.model.LogRequest;
import org.codejudge.sb.controller.model.Response;
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

    LogProcessingService logProcessingService;

    @Autowired
    public AppController(LogProcessingService logProcessingService) {
        this.logProcessingService = logProcessingService;
    }

    @PostMapping("/api/process-logs/")
    @ResponseBody
    public ResponseEntity<Response> processLogs(@RequestBody LogRequest logRequest) throws IOException {
        log.info("LogRequest is " + logRequest.toString());

        ErrorResponse errorResponse = logRequest.validate();
        if (errorResponse != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        return ResponseEntity.status(HttpStatus.OK).body(logProcessingService.processLogs(
                logRequest.getLogFiles(),
                logRequest.getParallelFileProcessingCount()));
    }

}
