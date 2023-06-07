package com.bharadwaj.demoone.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class CustomErrorResponse {

    private String field;
    private String message;

    public CustomErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    // getters and setters

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

