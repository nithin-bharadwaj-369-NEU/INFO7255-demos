package com.bharadwaj.demoone.repository;

import com.bharadwaj.demoone.model.Plan;

import java.util.List;

public interface MedicalPlanRepository {
    boolean savePlan(Plan p);

    List<Plan> fetchAllPlans();

    Plan getPlanById(String objectId);

    boolean deletePlan(String objectId);
}
