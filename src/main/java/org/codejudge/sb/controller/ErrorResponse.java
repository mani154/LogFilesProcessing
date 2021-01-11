package org.codejudge.sb.controller;

public class ErrorResponse {
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
