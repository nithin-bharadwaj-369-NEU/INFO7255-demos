package com.bharadwaj.demoone.repository;

import com.bharadwaj.demoone.model.Plan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class MedicalPlanRepositoryImpl {
    private List<Plan> list = new ArrayList<Plan>();

    public Plan save(Plan p) {
        Plan medicalPlan = new Plan();
        log.info("Plan sent to save : " + p.toString());
        // update the keys to the medical Plan
        list.add(medicalPlan);
        return p;
    }

    public int delete(String id) {
        list.removeIf(x -> x.getObjectId() == (id));
        return 1;
    }

//    public List<Plan> getAllPlans(){
//
//    }

}
