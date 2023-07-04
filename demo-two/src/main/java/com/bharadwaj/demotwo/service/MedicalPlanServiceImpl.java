package com.bharadwaj.demotwo.service;

import com.bharadwaj.demotwo.model.Plan;
import com.bharadwaj.demotwo.repository.MedicalPlanRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MedicalPlanServiceImpl implements MedicalPlanService{

    @Autowired
    private MedicalPlanRepository medicalPlanRepository;


    @Override
    public boolean savePlan(Plan p) {
        return medicalPlanRepository.savePlan(p);
    }

    @Override
    public List<Plan> fetchAllPlans() {
        return medicalPlanRepository.fetchAllPlans();
    }

    @Override
    public Optional<Plan> getMedicalPlanById(String objectId) {
        return medicalPlanRepository.getPlanById(objectId);
    }

    @Override
    public Optional<Boolean> deletePlan(String objectId) {
        return medicalPlanRepository.deletePlan(objectId);
    }

    @Override
    public Optional<Boolean> updatePlan(String objectId, Plan p) {
        return medicalPlanRepository.updatePlanById(objectId, p);
    }

    @Override
    public Optional<Plan> patchMedicalPlan(String objectId, ObjectNode updates) throws IOException {
        return medicalPlanRepository.patchPlan(objectId, updates);
    }
}
