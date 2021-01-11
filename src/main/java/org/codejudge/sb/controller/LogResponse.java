package org.codejudge.sb.controller;

import java.util.*;

public class LogResponse {
    private List<ExceptionResponse> response;

    public LogResponse() {
        this.response = new ArrayList<>();
    }
}

class ExceptionResponse {
    private List<ExceptionDetails> logs;
}

class ExceptionDetails {
    private String exception;
    private int count;
}