package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.entities.GroceryItem;
import com.example.demo.repositories.GroceryItemRepository;

@Service
public class GroceryItemService {

    @Autowired
    private GroceryItemRepository repository;
    public GroceryItem addGroceryItem(GroceryItem item) 
    {
        return repository.save(item);
    }
    public List<GroceryItem> getAllGroceryItems() 
    {
        return repository.findAll();
    }
    public Optional<GroceryItem> getGroceryItemById(int id) 
    {
        return repository.findById(id);
    }
    public Page<GroceryItem> paginated(PageRequest pageable) {
        return repository.findAll(pageable);
    }
    public GroceryItem updateGroceryItem(int id, GroceryItem updatedItem) 
    {
        return repository.findById(id).map(item -> {
            item.setName(updatedItem.getName());
            item.setQuantity(updatedItem.getQuantity());
            item.setPrice(updatedItem.getPrice());
            return repository.save(item);
        }).orElse(null);
    }
    public boolean deleteGroceryItem(int id) 
    {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public GroceryItem getByName(String name) {
        return repository.findByName(name);
    }

    public List<GroceryItem> getByPrice(double price) {
        return repository.findByPrice(price);
    }

    public List<GroceryItem> getByPriceLessThan(double price) {
        return repository.findByPriceLessThan(price);
    }

    public List<GroceryItem> getByPriceGreaterThan(double price) {
        return repository.findByPriceGreaterThan(price);
    }

    public List<GroceryItem> getByPriceRange(double minPrice, double maxPrice) {
        return repository.findByPriceRange(minPrice, maxPrice);
    }
}
