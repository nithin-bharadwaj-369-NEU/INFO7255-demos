package com.bharadwaj.demotwo.controller;

import com.bharadwaj.demotwo.dto.Message;
import com.bharadwaj.demotwo.dto.PlanResponse;
import com.bharadwaj.demotwo.exception.NoTokenException;
import com.bharadwaj.demotwo.model.Plan;
import com.bharadwaj.demotwo.service.MedicalPlanService;
import com.bharadwaj.demotwo.util.EtagUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/v1/plan")
public class MedicalPlanController {

    @Autowired
    private MedicalPlanService medicalPlanService;

    @PostMapping
    public ResponseEntity<PlanResponse> saveMedicalPlan(@Valid @RequestBody Plan p){
        boolean result = medicalPlanService.savePlan(p);
        if(result && p.getObjectId() != null){
            HttpHeaders responseHeaders = new HttpHeaders();
            String eTag = "\"" + EtagUtil.generateEtag(p) + "\"";
            responseHeaders.setETag(eTag);
            return ResponseEntity.status(201).headers(responseHeaders).body(new PlanResponse(p.getObjectId()));
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

    @GetMapping("/{objectId}")
    public ResponseEntity<Plan> getMedicalPlan(@PathVariable String objectId,
                                               @RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch){
        Optional<Plan> plan = medicalPlanService.getMedicalPlanById(objectId);
        if(plan.isPresent()){
            String currentETag = "\"" + EtagUtil.generateEtag(plan.get()) + "\"";
            if(ifNoneMatch != null && currentETag.equals(ifNoneMatch)){
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setETag(currentETag);

            return ResponseEntity.ok().headers(responseHeaders).body(plan.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{objectId}")
    public ResponseEntity<Message> updateMedicalPlan(@PathVariable String objectId, @Valid @RequestBody Plan p,
                                                     @RequestHeader(value = "If-Match", required = false) String ifMatch){

        Optional<Plan> plan = medicalPlanService.getMedicalPlanById(objectId);
        String originalETag = "\"" + EtagUtil.generateEtag(plan.get()) + "\"";
        if (ifMatch == null || ifMatch == "") {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new Message("eTag not Provided in request !!!"));
        }
        else if (!ifMatch.equals(originalETag)){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new Message("Plan has been updated by another User !!!"));
        }

        Optional<Boolean> result = medicalPlanService.updatePlan(objectId, p);
        if(result.isPresent() && result.get()){
            String eTag = "\"" + EtagUtil.generateEtag(p) + "\"";
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setETag(eTag);
            return ResponseEntity.status(200).headers(responseHeaders).body(new Message("Updated Successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("/{objectId}")
    public ResponseEntity<Object> patchMedicalPlan(@PathVariable String objectId, @RequestBody ObjectNode updates,
                                                 @RequestHeader(value = "If-Match", required = false) String ifMatch) throws IOException {
        Optional<Plan> oldPlan = medicalPlanService.getMedicalPlanById(objectId);
        String originalETag = "\"" + EtagUtil.generateEtag(oldPlan.get()) + "\"";
        if (ifMatch == null || ifMatch == "") {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new Message("eTag not Provided in request !!!"));
        }
        else if (!ifMatch.equals(originalETag)){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new Message("Plan has been updated by another User !!!"));
        }

        Optional<Plan> plan = medicalPlanService.patchMedicalPlan(objectId, updates);
        if(plan.isPresent()){
            String currentETag = "\"" + EtagUtil.generateEtag(plan.get()) + "\"";

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setETag(currentETag);

            return ResponseEntity.ok().headers(responseHeaders).body(plan.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{objectId}")
    public ResponseEntity<Object> deleteMedicalPlan(@PathVariable String objectId, @RequestHeader(value = "If-Match", required = false) String ifMatch){
        log.info("Object passed to delete Medical Plan : " + objectId);
        
        Optional<Plan> oldPlan = medicalPlanService.getMedicalPlanById(objectId);
        String originalETag = "\"" + EtagUtil.generateEtag(oldPlan.get()) + "\"";
        if (ifMatch == null || ifMatch == "") {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new Message("eTag not Provided in request !!!"));
        }
        else if (!ifMatch.equals(originalETag)){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new Message("Plan has been updated by another User !!!"));
        }

        Optional<Boolean> result = medicalPlanService.deletePlan(objectId);
        if(result.isPresent()){
            if(result.get()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Plan with Object ID : %s Not found", objectId));
        }
    }
}
