package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.GroceryItem;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Integer> {

   public GroceryItem findByName(String name);
   @Query("SELECT g FROM GroceryItem g WHERE g.price = :price")
    List<GroceryItem> findByPrice(@Param("price") double price);

    @Query("SELECT g FROM GroceryItem g WHERE g.price < :price")
    List<GroceryItem> findByPriceLessThan(@Param("price") double price);

    @Query("SELECT g FROM GroceryItem g WHERE g.price > :price")
    List<GroceryItem> findByPriceGreaterThan(@Param("price") double price);

    @Query("SELECT g FROM GroceryItem g WHERE g.price BETWEEN :minPrice AND :maxPrice")
    List<GroceryItem> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
