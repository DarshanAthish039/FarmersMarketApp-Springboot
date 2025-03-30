package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository rep;

    public User addUser(User u) 
    {
        if (u.getId() < 0) 
        {
            System.out.println("ID should be positive! Received ID: " + u.getId());
        }
        if (u.getId() <= 0) 
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be positive!");
        }
        if (u.getName() == null || u.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name must not be empty!");
        }
                if (u.getEmail() == null || !u.getEmail().contains("@") || !u.getEmail().endsWith(".com")) {
            System.out.println("Invalid email format: " + u.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email format! Email must contain '@' and end with '.com'");
        }
        if (rep.existsByName(u.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this name already exists!");
        }

        return rep.save(u);
    }

    public List<User> getUsers() {
        return rep.findAll();
    }

    public Optional<User> getUserById(int id) {
        return rep.findById(id);
    }

    public Page<User> paginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return rep.findAll(pageable);
    }

    public User updateUser(int id, User updatedUser) {
        if (id < 0) {
            System.out.println("ID should be positive! Received ID: " + id);
        }
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be positive!");
        }
        if (updatedUser.getName() == null || updatedUser.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name must not be empty!");
        }
        if (updatedUser.getEmail() == null || !updatedUser.getEmail().contains("@") || !updatedUser.getEmail().endsWith(".com")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email format!");
        }
        updatedUser.setId(id);
        return rep.save(updatedUser);
    }

    public boolean deleteUser(int id) {
        if (rep.existsById(id)) {
            rep.deleteById(id);
            return true;
        }
        return false;
    }

    public User getByName(String name) {
        return rep.findByName(name);
    }

    private static UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        UserService.userRepository = userRepository;
    }

    public static List<User> searchUsersByCharacter(String character) {
        return userRepository.findBySingleCharacter(character);
    }
}
