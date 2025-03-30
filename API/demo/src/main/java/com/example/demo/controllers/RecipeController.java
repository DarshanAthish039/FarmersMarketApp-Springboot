package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Recipe;
import com.example.demo.services.RecipeService;

@RestController
@RequestMapping(path="/rcp")
public class RecipeController 
{

    @Autowired
    private RecipeService service;
    //http://localhost:8080/rcp/recipes
    @GetMapping("/recipes")
    public List<Recipe> getRecipes()
    {
        return service.getRecipes();
    }
    //http://localhost:8080/rcp/recipes/2
    @GetMapping("/recipes/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable int id)
    {
        return service.getRecipeById(id);
    }
    //http://localhost:8080/rcp/recipe?page=1&size=3
    @GetMapping("/recipe")
    public Page<Recipe> paginated(@RequestParam int page ,@RequestParam int size)
    {
        return service.paginated(page, size);
    }
    //http://localhost:8080/rcp/recipe
    @PostMapping("/recipes")
    public Recipe addRecipe(@RequestBody Recipe recipe)
    {
        return service.addRecipe(recipe);
    }
    //http://localhost:8080/rcp/recipes/3
    @PutMapping("/recipes/{id}")
    public Recipe updateRecipe(@PathVariable int id, @RequestBody Recipe updatedRecipe)
    {
        return service.updateRecipe(id, updatedRecipe);
    }
    //http://localhost:8080/rcp/recipes/6
    @DeleteMapping("/recipes/{id}")
    public String deleteRecipe(@PathVariable int id) 
    {
        return service.deleteRecipe(id) ? "Recipe Deleted" : "Recipe Not Found";
    }
    //http://localhost:8080/rcp/recipe/search/Spaghetti Bolognese
    @GetMapping("/recipe/search/{name}")
    public Recipe searchRecipe(@PathVariable String name)
    {
        return  service.getByName(name);
    }
    //http://localhost:8080/rcp/recipe/searchName/Salad
    @GetMapping("/recipe/searchName/{name}")
    public List<Recipe> searchByName(@PathVariable String name) 
    {
        return service.searchRecipesByName(name);
    }
    
}
