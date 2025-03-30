package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.MealPlan;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Integer> {

public MealPlan findByStartDate(LocalDate endDate);

@Query("SELECT m FROM MealPlan m WHERE m.endDate = :endDate")
    List<MealPlan> findByEndDate(@Param("endDate") LocalDate endDate);

}
