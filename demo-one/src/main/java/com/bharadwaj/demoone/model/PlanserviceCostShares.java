package com.bharadwaj.demoone.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class PlanserviceCostShares implements Serializable {
    @Min(value = 0, message = "Deductible must be greater than 0")
    @Max(value = 999999999, message = "Deductible must be less than 999999999")
    public int deductible;

    @Min(value = 0, message = "Co-pay must be greater than 0")
    @Max(value = 999999999, message = "Co-pay must be less than 999999999")
    public int copay;
    @NotBlank(message = "Organization is mandatory")
    public String _org;
    @NotBlank(message = "Object ID is mandatory")
    public String objectId;
    @NotBlank(message = "Object type is mandatory")
    public String objectType;

}
