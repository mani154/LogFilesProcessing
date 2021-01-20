package org.codejudge.sb.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public class LogRequest {
    List<String> logFiles;
    int parallelFileProcessingCount;

    public ErrorResponse validate() {
        if(parallelFileProcessingCount <= 0) {
            return new ErrorResponse("failure", "Parallel File Processing count must be greater than zero!");
        }
        else if(logFiles == null || logFiles.size() < parallelFileProcessingCount) {
            return new ErrorResponse("failure", "Log files cannot be empty or less than parallelFileProcessingCount");
        }
        else {
            return null;
        }
    }
}