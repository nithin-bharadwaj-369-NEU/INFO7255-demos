package com.bharadwaj.demoone.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public class LinkedPlanService {
    @Valid
    public LinkedService linkedService;
    @Valid
    public PlanserviceCostShares planserviceCostShares;
    @NotBlank(message = "Organization is mandatory")
    public String _org;

    @NotBlank(message = "Object ID is mandatory")
    public String objectId;
    @NotBlank(message = "Object type is mandatory")
    public String objectType;

}
