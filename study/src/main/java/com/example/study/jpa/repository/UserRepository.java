package com.example.study.jpa.repository;

import com.example.study.jpa.dao.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    User findByFirstName(String firstName);
}
