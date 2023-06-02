package com.bharadwaj.demoone.controller;

import com.bharadwaj.demoone.model.Plan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MedicalPlanController {

    @PostMapping("/v1/plan")
    public void addMedicalPlan(){

    }

    @GetMapping("/v1/plan/{objectId}")
    public String getMedicalPlanDetails(@PathVariable String objectId){
        return objectId;
    }

    @DeleteMapping("/v1/plan/{objectId}")
    public void deleteMedicalPlan(@PathVariable String objectId){
        
    }
}
