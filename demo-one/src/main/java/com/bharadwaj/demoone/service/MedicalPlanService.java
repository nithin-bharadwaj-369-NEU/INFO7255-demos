package com.bharadwaj.demoone.service;

import com.bharadwaj.demoone.model.Plan;

import java.util.List;

public interface MedicalPlanService {
    boolean savePlan(Plan p);

    List<Plan> fetchAllPlans();

    Plan getMedicalPlanById(String objectId);

    boolean deletePlan(String objectId);
}
