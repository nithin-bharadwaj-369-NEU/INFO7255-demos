package com.bharadwaj.demoone.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@RedisHash("Plan")
public class Plan implements Serializable {
    @Valid
    public PlanCostShares planCostShares;
    @Valid
    public ArrayList<LinkedPlanService> linkedPlanServices;
    @NotBlank(message = "Organization is mandatory")
    public String _org;
    @Id
    @NotBlank(message = "Object ID is mandatory")
    public String objectId;
    @NotBlank(message = "Object type is mandatory")
    public String objectType;

    @NotBlank(message = "Plan type is mandatory")
    public String planType;

    @NotBlank(message = "Creation date is mandatory")
    @DateTimeFormat(pattern = "dd-MM-yyyy") // update this if your date format is different
    @PastOrPresent(message = "Creation date must be in the past or present")
    public Date creationDate;
}
