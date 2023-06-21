package com.dc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dc.entity.PlanEntity;

@Repository
public interface PlanRepo extends JpaRepository<PlanEntity, Integer>{

}
