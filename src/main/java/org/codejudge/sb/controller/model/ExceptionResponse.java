package org.codejudge.sb.controller.model;

import java.util.List;

public class ExceptionResponse {

    private String hourMinutes;
    private List<ExceptionDetails> logs;

    public List<ExceptionDetails> getLogs() {
        return logs;
    }
}
