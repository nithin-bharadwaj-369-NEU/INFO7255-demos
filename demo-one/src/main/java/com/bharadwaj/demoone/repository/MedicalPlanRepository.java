package com.bharadwaj.demoone.repository;

import com.bharadwaj.demoone.model.Plan;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MedicalPlanRepository {
    boolean savePlan(Plan p);

    List<Plan> fetchAllPlans();

    Optional<Plan> getPlanById(String objectId);

    Optional<Boolean> deletePlan(String objectId);
}
