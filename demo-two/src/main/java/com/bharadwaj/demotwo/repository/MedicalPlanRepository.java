package com.bharadwaj.demotwo.repository;

import com.bharadwaj.demotwo.model.Plan;

import java.util.List;
import java.util.Optional;

public interface MedicalPlanRepository {
    boolean savePlan(Plan p);

    List<Plan> fetchAllPlans();

    Optional<Plan> getPlanById(String objectId);

    Optional<Boolean> deletePlan(String objectId);
}
