package com.bharadwaj.demoone.controller;

import com.bharadwaj.demoone.model.Plan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MedicalPlanController {

    @PostMapping("/v1/plan")
    public ResponseEntity<Plan> addMedicalPlan(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/plan/{objectId}")
    public ResponseEntity<Plan> getMedicalPlanDetails(@PathVariable String objectId){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/plan/{objectId}")
    public ResponseEntity<Plan> deleteMedicalPlan(@PathVariable String objectId){
        return ResponseEntity.ok().build();
    }
}
