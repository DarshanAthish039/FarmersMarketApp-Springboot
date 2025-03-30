package com.example.demo.controllers;

import com.example.demo.entities.GroceryItem;
import com.example.demo.services.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/gro")
public class GroceryItemController 
{

    @Autowired
    private GroceryItemService service;
    @GetMapping("/grocery")
    public List<GroceryItem> getAllItems()
    {
        return service.getAllGroceryItems();
    }
    //http://localhost:8080/gro/grocery
    @GetMapping("/grocery/{id}")  
    public Optional<GroceryItem> getItemById(@PathVariable int id)
    {
        return service.getGroceryItemById(id);
    }
    //http://localhost:8080/gro/paginated?page=2&size=3
    //http://localhost:8080/gro/paginated?page=0&size=10&sort=price,desc
    @GetMapping("/paginated")
    public Page<GroceryItem> paginated(@RequestParam int page,@RequestParam int size,@RequestParam(defaultValue = "id,asc") String[] sort) 
    {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return service.paginated(pageable);
    }
    //http://localhost:8080/gro/grocery
    @PostMapping("/grocery")
    public GroceryItem addItem(@RequestBody GroceryItem item) 
    {
        return service.addGroceryItem(item);
    }//http://localhost:8080/gro/grocery/6
    @PutMapping("/grocery/{id}")
    public GroceryItem updateItem(@PathVariable int id, @RequestBody GroceryItem updatedItem) 
    {
        return service.updateGroceryItem(id, updatedItem);
    }
    //http://localhost:8080/gro/grocery/7
    @DeleteMapping("/grocery/{id}")
    public String deleteItem(@PathVariable int id) 
    {
        return service.deleteGroceryItem(id) ? "Item Deleted" : "Item Not Found";
    }
    //http://localhost:8080/gro/grocery/search/Eggs
    @GetMapping("/grocery/search/{name}")
    public GroceryItem searchGroceryItem(@PathVariable String name)
    {
        return service.getByName(name);
    }
    //http://localhost:8080/gro/grocery/price/1.99
    @GetMapping("/grocery/price/{price}")
    public List<GroceryItem> searchByPrice(@PathVariable double price) {
        return service.getByPrice(price);
    }
    //http://localhost:8080/gro/grocery/price/less/5.00
    @GetMapping("/grocery/price/less/{price}")
    public List<GroceryItem> searchByPriceLessThan(@PathVariable double price) {
        return service.getByPriceLessThan(price);
    }
    //http://localhost:8080/gro/grocery/price/greater/6.0   
     @GetMapping("/grocery/price/greater/{price}")
    public List<GroceryItem> searchByPriceGreaterThan(@PathVariable double price) {
        return service.getByPriceGreaterThan(price);
    }
    //http://localhost:8080/gro/grocery/price/range?minPrice=5.00&maxPrice=8.0
    @GetMapping("/grocery/price/range")
    public List<GroceryItem> searchByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return service.getByPriceRange(minPrice, maxPrice);
    }
}