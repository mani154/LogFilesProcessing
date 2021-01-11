package org.codejudge.sb.controller.model;

import java.util.*;

public class LogResponse {
    private final List<ExceptionResponse> response;

    public List<ExceptionResponse> getResponse() {
        return response;
    }
    public LogResponse() {
        this.response = new ArrayList<>();
    }
}