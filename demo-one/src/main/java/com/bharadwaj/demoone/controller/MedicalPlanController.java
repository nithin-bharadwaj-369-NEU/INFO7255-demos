package com.bharadwaj.demoone.controller;

import com.bharadwaj.demoone.model.Plan;
import com.bharadwaj.demoone.service.MedicalPlanService;
import com.bharadwaj.demoone.service.MedicalPlanServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/plan")
public class MedicalPlanController {

    @Autowired
    private MedicalPlanService medicalPlanService;

    @PostMapping
    public ResponseEntity<Plan> saveMedicalPlan(@Valid @RequestBody Plan p){
        log.info("Plan passed to POST : ", p );
        boolean result = medicalPlanService.savePlan(p);
        if(result){
            ResponseEntity.ok("Medical Plan added to the DB Successfully !! ");
        }else{
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return null;
    }


    @GetMapping
    public ResponseEntity<List<Plan>> fetchAllPlans(){
        List<Plan> plans;
        plans = medicalPlanService.fetchAllPlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("{objectId}")
    public ResponseEntity<Plan> getMedicalPlan(@PathVariable String objectId){
        Plan p;
        p = medicalPlanService.getMedicalPlanById(objectId);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("{objectId}")
    public ResponseEntity<Plan> deleteMedicalPlan(@PathVariable String objectId){
        log.info("Object passed to delete Medical Plan : " + objectId);
        return ResponseEntity.ok().build();
    }
}
