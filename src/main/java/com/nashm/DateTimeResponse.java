package com.nashm;

// Response DTO (Data Transfer Object)
class DateTimeResponse {
    private String currentDateTime;

    public DateTimeResponse(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}
