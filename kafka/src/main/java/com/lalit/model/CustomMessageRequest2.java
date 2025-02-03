package com.lalit.model;

import java.time.LocalDateTime;

public class CustomMessageRequest2 {
    private String message;
    private LocalDateTime createdDate;
    private String createdBy;


    // Constructors
    public CustomMessageRequest2() {}

    public CustomMessageRequest2(String message, LocalDateTime createdDate, String createdBy) {
        this.message = message;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "CustomMessageRequest2{" +
                "message='" + message + '\'' +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
