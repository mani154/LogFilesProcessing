package org.codejudge.sb.controller;

import org.codejudge.sb.controller.model.Response;

import java.util.*;

public class ExceptionResponse extends Response {
    private List<ExceptionDetails> logs;

    public List<ExceptionDetails> getLogs() {
        return logs;
    }

    public void setLogs(List<ExceptionDetails> logs) {
        this.logs = logs;
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
    public ExceptionResponse(List<ExceptionDetails> logs) {
        this.logs = logs;
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
    private String timeBucket;
    private List<ExceptionLog> logs;

    public ExceptionDetails(String timeBucket, List<ExceptionLog> logs) {
        this.timeBucket = timeBucket;
        this.logs = logs;
    }

    public void setTimeBucket(String timeBucket) {
        this.timeBucket = timeBucket;
    }

    public void setLogs(List<ExceptionLog> logs) {
        this.logs = logs;
    }

    public String getTimeBucket() {
        return timeBucket;
    }

    public List<ExceptionLog> getLogs() {
        return logs;
    }
}