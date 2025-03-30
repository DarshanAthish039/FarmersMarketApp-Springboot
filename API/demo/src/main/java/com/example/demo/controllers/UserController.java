package com.example.demo.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
@RestController
@RequestMapping(path = "/usr")
public class UserController 
{
    @Autowired
    private UserService service;
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonParseError(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("ID must be a positive number");
    }
    @GetMapping("/user")
    public List<User> getUsers() 
    {
        return service.getUsers();
    }
    //http://localhost:8080/usr/user/6
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) 
    {
        if(id<1) 
        {
            return ResponseEntity.badRequest().body("Invalid ID: ID must be a positive number.");
        }
        Optional<User> user = service.getUserById(id);
        return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.badRequest().body("User not found.");
    }
    //http://localhost:8080/usr/person?page=1&size=2
    @GetMapping("/person")
    public Page<User> paginated(@RequestParam int page, @RequestParam int size) 
    {
        return service.paginated(page, size);
    }
    //http://localhost:8080/usr/user
    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User u) 
    {
        if (u.getId() < 1) 
        {
            return ResponseEntity.badRequest().body("Invalid ID: ID must be a positive number.");
        }
        try {
            service.addUser(u);
            return ResponseEntity.ok("User added successfully!");
        } 
        catch (ResponseStatusException e) 
        {
            return ResponseEntity.badRequest().body(e.getReason());
        }
    }
    //http://localhost:8080/usr/user/6
    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User updatedUser) 
    {
        if (id < 1) 
        {
            return ResponseEntity.badRequest().body("ID must be a positive number");
        }
        try 
        {
            service.updateUser(id, updatedUser);
            return ResponseEntity.ok("User updated successfully!");
        } 
        catch (ResponseStatusException e) 
        {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
    }
    //http://localhost:8080/usr/user/6
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) 
    {
        if (id < 1) 
        {
            return ResponseEntity.badRequest().body("Invalid ID: ID must be a positive number.");
        }
        return service.deleteUser(id) ? ResponseEntity.ok("User Deleted") : ResponseEntity.badRequest().body("User Not Found");
    }
    // http://localhost:8080/usr/user/search/gowtham
    @GetMapping("/user/search/{name}")
    public ResponseEntity<?> searchUser(@PathVariable String name) 
    {
        User user = service.getByName(name);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.badRequest().body("User not found.");
    }
    // http://localhost:8080/usr/users/search?name=G
    @GetMapping("/users/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String name) 
    {
        List<User> users = UserService.searchUsersByCharacter(name);
        return ResponseEntity.ok(users);
    }
}