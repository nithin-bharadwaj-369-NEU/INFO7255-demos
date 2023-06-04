package com.bharadwaj.demoone.controller;

import com.bharadwaj.demoone.model.Plan;
import com.bharadwaj.demoone.service.MedicalPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/plan")
public class MedicalPlanController {

    @Autowired
    private MedicalPlanService medicalPlanService;

    @PostMapping
    public Plan addMedicalPlan(@RequestBody Plan p){
//        return ResponseEntity.ok().build();
        return medicalPlanService.savePlan(p);
    }

    @GetMapping
    public ResponseEntity<Plan> getMedicalPlanDetails(@PathVariable String objectId){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Plan> deleteMedicalPlan(@PathVariable String objectId){
        return ResponseEntity.ok().build();
    }
}
