package com.bharadwaj.demoone.service;

import com.bharadwaj.demoone.model.Plan;

import java.util.List;
import java.util.Optional;

public interface MedicalPlanService {
    boolean savePlan(Plan p);

    List<Plan> fetchAllPlans();

    Optional<Plan> getMedicalPlanById(String objectId);

    Optional<Boolean> deletePlan(String objectId);
}
