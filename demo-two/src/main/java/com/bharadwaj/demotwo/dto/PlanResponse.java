package com.bharadwaj.demotwo.dto;

public class PlanResponse {

    private String objectId;
    public PlanResponse(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
