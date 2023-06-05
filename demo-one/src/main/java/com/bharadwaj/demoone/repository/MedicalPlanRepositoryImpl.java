package com.bharadwaj.demoone.repository;

import com.bharadwaj.demoone.model.Plan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class MedicalPlanRepositoryImpl implements MedicalPlanRepository{

    private final RedisTemplate redisTemplate;
    @Autowired
    public MedicalPlanRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String KEY="MEDICAL_PLAN";
    @Override
    public boolean savePlan(Plan p) {
        try{
            redisTemplate.opsForHash().put(KEY, p.getObjectId().toString(), p);
            return true;
        }catch(Exception e){
            log.error("Error Saving plan : ", e);
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
    public Optional<Plan> getPlanById(String objectId) {
        Plan medicalPlan;
        medicalPlan = (Plan) redisTemplate.opsForHash().get(KEY, objectId.toString());
        return Optional.ofNullable(medicalPlan);
    }

    @Override
    public Optional<Boolean>  deletePlan(String objectId) {
        try{
            if(redisTemplate.opsForHash().hasKey(KEY, objectId.toString())){
                redisTemplate.opsForHash().delete(KEY, objectId.toString());
                return Optional.of(true);
            }else {
                return Optional.empty();
            }
        }catch(Exception e){
            log.error("Error deleting plan", e);
            return Optional.of(false);
        }
    }
}
