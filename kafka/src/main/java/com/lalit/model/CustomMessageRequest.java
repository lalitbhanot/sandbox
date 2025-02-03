package com.lalit.model;

import java.time.LocalDateTime;

public class CustomMessageRequest {

    private String message;
    private String createdBy;

    public CustomMessageRequest() {
    }

    public CustomMessageRequest(String message, String createdBy) {
        this.message = message;
        this.createdBy = createdBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


}

