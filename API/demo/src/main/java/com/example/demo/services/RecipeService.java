package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Recipe;
import com.example.demo.repositories.RecipeRepository;

@Service
public class RecipeService 
{

    @Autowired
    private RecipeRepository rep;
    public Recipe addRecipe(Recipe recipe) 
    {
        return rep.save(recipe);
    }
    public List<Recipe> getRecipes() 
    {
        return rep.findAll();
    }

    public RecipeRepository getRep() 
    {
        return rep;
    }

    public void setRep(RecipeRepository rep) 
    {
        this.rep = rep;
    }
    public Optional<Recipe> getRecipeById(int id) 
    {
        return rep.findById(id);
    }
     public Page<Recipe> paginated(int page,int size){
        Pageable pageable=PageRequest.of(page,size,Sort.by("name").ascending());
        return rep.findAll(pageable);
    }
    public Recipe updateRecipe(int id, Recipe updatedRecipe) 
    {
        return rep.findById(id).map(recipe -> {
            recipe.setName(updatedRecipe.getName());
            recipe.setInstructions(updatedRecipe.getInstructions());
            return rep.save(recipe);
        }).orElse(null);
    }
    public boolean deleteRecipe(int id) 
    {
        if (rep.existsById(id)) {
            rep.deleteById(id);
            return true;
        }
        return false;
    }
    public Recipe getByName(String name) {
        return rep.findByName(name);
    }
    public List<Recipe> searchRecipesByName(String name) {
        return rep.searchByName(name);
    }
}