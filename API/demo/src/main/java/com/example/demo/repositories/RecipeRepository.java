package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
   
    public Recipe findByName(String name);
    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Recipe> searchByName(@Param("name") String name);

}
