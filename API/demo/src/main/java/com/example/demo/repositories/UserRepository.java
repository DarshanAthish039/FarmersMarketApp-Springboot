package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> 
{
    public User findByName(String name);
    @Query("SELECT e FROM User e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :char, '%'))")
    List<User> findBySingleCharacter(@Param("char") String character);
    
    List<User> findByNameContaining(String character);
    boolean existsByName(String name);
}