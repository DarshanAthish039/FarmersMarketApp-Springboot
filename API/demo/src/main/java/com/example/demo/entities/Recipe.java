package com.example.demo.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String instructions;

    @ManyToMany(mappedBy = "recipes")
    private Set<MealPlan> mealPlans;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "recipe_groceryitem",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "groceryitem_id")
    )
    private Set<GroceryItem> groceryItems;

    public Recipe() {}

    public Recipe(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Set<MealPlan> getMealPlans() {
        return mealPlans;
    }

    public void setMealPlans(Set<MealPlan> mealPlans) {
        this.mealPlans = mealPlans;
    }

    public Set<GroceryItem> getGroceryItems() {
        return groceryItems;
    }

    public void setGroceryItems(Set<GroceryItem> groceryItems) {
        this.groceryItems = groceryItems;
    }
}
