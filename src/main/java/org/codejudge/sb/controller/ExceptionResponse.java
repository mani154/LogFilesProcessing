package org.codejudge.sb.controller;

import org.codejudge.sb.controller.model.Response;

import java.util.*;

public class ExceptionResponse extends Response {
    private List<ExceptionDetails> response;

    public List<ExceptionDetails> getResponse() {
        return response;
    }

    public void setResponse(List<ExceptionDetails> response) {
        this.response = response;
    }

    public static ExceptionResponse create(Map<String, Map<String, Integer>> result) {
        List<ExceptionDetails> exceptionDetailsList = new ArrayList<>();
        for(Map.Entry<String, Map<String, Integer>> entry: result.entrySet()) {
            List<ExceptionLog> logs = new ArrayList<>();
            for(Map.Entry<String, Integer> log: entry.getValue().entrySet()) {
                ExceptionLog newLog = new ExceptionLog(log.getKey(), log.getValue());
                logs.add(newLog);
            }
            exceptionDetailsList.add(new ExceptionDetails(entry.getKey(), logs));
        }
        return new ExceptionResponse(exceptionDetailsList);
    }
    public ExceptionResponse(List<ExceptionDetails> response) {
        this.response = response;
    }
}

class ExceptionLog {
    private String exception;
    private int count;

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ExceptionLog(String exception, int count) {
        this.exception = exception;
        this.count = count;
    }
}

class ExceptionDetails {
    private String timestamp;
    private List<ExceptionLog> logs;

    public ExceptionDetails(String timestamp, List<ExceptionLog> logs) {
        this.timestamp = timestamp;
        this.logs = logs;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setLogs(List<ExceptionLog> logs) {
        this.logs = logs;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public List<ExceptionLog> getLogs() {
        return logs;
    }
}