package com.bharadwaj.demoone.service;

import com.bharadwaj.demoone.model.Plan;
import com.bharadwaj.demoone.repository.MedicalPlanRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MedicalPlanServiceImpl {

    @Autowired
    private MedicalPlanRepositoryImpl medicalPlanRepositoryIml;

    public Plan savePlan(Plan p) {
        return medicalPlanRepositoryIml.save(p);
    }

//    public List<Plan> getProducts() {
//        return medicalPlanRepository.getAllPlans();
//    }
//
//    public Plan getProductById(int id) {
//        return medicalPlanRepository.(id);
//    }

    public String deleteProduct(String id) {
        medicalPlanRepositoryIml.delete(id);
        return "product removed !! " + id;
    }
}
