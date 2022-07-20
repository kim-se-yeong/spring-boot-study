package com.example.study.user;

import com.example.study.jpa.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {
    
    @InjectMocks
    private UserService userService;

    @Test
    void save() {
        String lastName = "kim";
        userService.set(lastName);
    }
}