package com.bharadwaj.demoone.repository;

import com.bharadwaj.demoone.model.Plan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class MedicalPlanRepositoryImpl implements MedicalPlanRepository{

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY="MEDICAL_PLAN";
    @Override
    public boolean savePlan(Plan p) {
        try{
            redisTemplate.opsForHash().put(KEY, p.getObjectId().toString(), p);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Plan> fetchAllPlans() {
        List<Plan> plans;
        plans = redisTemplate.opsForHash().values(KEY);
        return plans;
    }

    @Override
    public Plan getPlanById(String objectId) {
        Plan medicalPlan;
        medicalPlan = (Plan) redisTemplate.opsForHash().get(KEY, objectId.toString());
        return medicalPlan;
    }

    @Override
    public boolean deletePlan(String objectId) {
        try{
            redisTemplate.opsForHash().delete(KEY, objectId.toString());
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
