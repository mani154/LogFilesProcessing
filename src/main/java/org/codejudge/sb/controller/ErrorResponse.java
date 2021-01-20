package org.codejudge.sb.controller;

import org.codejudge.sb.controller.model.Response;

public class ErrorResponse extends Response {
    private String status;
    private String reason;

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public ErrorResponse(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }
}


