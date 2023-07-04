package com.bharadwaj.demotwo.repository;

import com.bharadwaj.demotwo.model.Plan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MedicalPlanRepository {
    boolean savePlan(Plan p);

    List<Plan> fetchAllPlans();

    Optional<Plan> getPlanById(String objectId);

    Optional<Boolean> deletePlan(String objectId);

    Optional<Boolean> updatePlanById(String objectId, Plan p);

    Optional<Plan> patchPlan(String objectId, ObjectNode updates) throws IOException;
}
