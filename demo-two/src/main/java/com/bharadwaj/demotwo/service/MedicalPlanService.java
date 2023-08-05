package com.bharadwaj.demotwo.service;

import com.bharadwaj.demotwo.model.Plan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MedicalPlanService {
    boolean savePlan(Plan p);

    List<Plan> fetchAllPlans();

    Optional<Plan> getMedicalPlanById(String objectId);

    Optional<Boolean> deletePlan(String objectId) throws JsonProcessingException;

    Optional<Boolean> updatePlan(String objectId, Plan p);

    Optional<Plan> patchMedicalPlan(String objectId, ObjectNode updates) throws IOException;
}
