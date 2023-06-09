package com.bharadwaj.demoone.service;

import com.bharadwaj.demoone.model.Plan;
import com.bharadwaj.demoone.repository.MedicalPlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
