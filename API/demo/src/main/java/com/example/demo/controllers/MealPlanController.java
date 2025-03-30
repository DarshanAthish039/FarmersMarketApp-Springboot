package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.MealPlan;
import com.example.demo.services.MealPlanService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path="/mel")

public class MealPlanController 
{

    @Autowired
    private MealPlanService service;
    @GetMapping("/meal")
    public List<MealPlan> getMealPlans()
    {
        return service.getMealPlans();
    }
    //http://localhost:8080/mel/meal
    @GetMapping("/meal/{id}")
    public Optional<MealPlan> getMealPlanById(@PathVariable int id)
    {
        return service.getMealPlanById(id);
    }
    //http://localhost:8080/mel/mealplan?page=1&size=3
    @GetMapping("/mealplan")
    public Page<MealPlan>paginated(@RequestParam int page ,@RequestParam int size)
    {
        return service.paginated(page, size);
    }
    //http://localhost:8080/mel/meal
    @PostMapping("/meal")
    public MealPlan addMealPlan(@RequestBody MealPlan mealPlan)
    {
        return service.addMealPlan(mealPlan);
    }
    //http://localhost:8080/mel/meal/3
    @PutMapping("/meal/{id}")
    public MealPlan updateMealPlan(@PathVariable int id, @RequestBody MealPlan updatedMealPlan)
    {
        return service.updateMealPlan(id, updatedMealPlan);
    }
    //http://localhost:8080/mel/meal/7
    @DeleteMapping("/meal/{id}")
    public String deleteMealPlan(@PathVariable int id)
    {
        return service.deleteMealPlan(id) ? "Meal Plan Deleted" : "Meal Plan Not Found";
    }
    //http://localhost:8080/mel/meal/search/2024-03-01
    @GetMapping("/meal/search/{startDate}")
    public MealPlan searchByMealPlan(@PathVariable LocalDate startDate)
    {
        return service.getBystartDate(startDate);
    }
    // http://localhost:8080/mel/meal/enddate/2024-03-01
    @GetMapping("/meal/enddate/{endDate}")
    public List<MealPlan> searchByEndDate(@PathVariable LocalDate endDate) 
    {
        return service.getByEndDate(endDate);
    }
}
    




