package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.entities.MealPlan;
import com.example.demo.repositories.MealPlanRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MealPlanService {

    @Autowired
    private MealPlanRepository repository;
    public MealPlan addMealPlan(MealPlan mealPlan) 
    {
        return repository.save(mealPlan);
    }
    public List<MealPlan> getMealPlans() 
    {
        return repository.findAll();
    }
    public Page<MealPlan> paginated(int page,int size)
    {
        Pageable pageable=PageRequest.of(page,size,Sort.by("startDate").descending());
        return repository.findAll(pageable);
    }
    public Optional<MealPlan> getMealPlanById(int id) 
    {
        return repository.findById(id);
    }
    public MealPlan updateMealPlan(int id, MealPlan updatedMealPlan) 
    {

        return repository.findById(id).map(mealPlan -> {
            mealPlan.setTitle(updatedMealPlan.getTitle());
            mealPlan.setStartDate(updatedMealPlan.getStartDate());
            mealPlan.setEndDate(updatedMealPlan.getEndDate());
            return repository.save(mealPlan);
        }).orElse(null);
    }
    public boolean deleteMealPlan(int id) 
    {
        if (repository.existsById(id)) 
        {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public MealPlan getBystartDate(LocalDate Date) {
        return repository.findByStartDate(Date);
    }
    public List<MealPlan> getByEndDate(LocalDate endDate) {
        return repository.findByEndDate(endDate);
    }
    
    
}
