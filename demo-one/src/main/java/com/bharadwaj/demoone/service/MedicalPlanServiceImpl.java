package com.bharadwaj.demoone.service;

import com.bharadwaj.demoone.model.Plan;
import com.bharadwaj.demoone.repository.MedicalPlanRepository;
import com.bharadwaj.demoone.repository.MedicalPlanRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Plan getMedicalPlanById(String objectId) {
        return medicalPlanRepository.getPlanById(objectId);
    }
}
