package com.bharadwaj.demoone.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class LinkedPlanService implements Serializable {
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
