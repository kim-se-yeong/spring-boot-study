package com.example.study.jpa.service;

import com.example.study.jpa.dao.User;
import com.example.study.jpa.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private UserRepository userRepository;

    public void set(String lastName) {
        // User user = User.builder()
        //     .id(0L)
        //     .firstName("name")
        //     .lastName(lastName)
        //     .build();
        // userRepository.save(user);
    }
}