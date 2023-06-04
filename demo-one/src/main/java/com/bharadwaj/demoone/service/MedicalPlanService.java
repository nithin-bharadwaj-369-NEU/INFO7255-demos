package com.bharadwaj.demoone.service;

import com.bharadwaj.demoone.model.Plan;
import com.bharadwaj.demoone.repository.MedicalPlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MedicalPlanService {

    @Autowired
    private MedicalPlanRepository medicalPlanRepository;

    public Plan savePlan(Plan p) {
        return medicalPlanRepository.save(p);
    }

//    public List<Plan> getProducts() {
//        return medicalPlanRepository.getAllPlans();
//    }
//
//    public Plan getProductById(int id) {
//        return medicalPlanRepository.(id);
//    }

    public String deleteProduct(String id) {
        medicalPlanRepository.delete(id);
        return "product removed !! " + id;
    }
}
