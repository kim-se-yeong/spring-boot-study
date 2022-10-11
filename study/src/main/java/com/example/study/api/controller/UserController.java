package com.example.study.api.controller;

import com.example.study.api.dto.User;

import com.example.study.jpa.dao.UserView;
import com.example.study.jpa.repository.UserViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserViewRepository userViewRepository;

    @PostMapping("/save")
    public User get(@RequestBody User user) {
        log.info("user name {}, {}", user.getUserName().getLastName(), user.getUserName().getFirstName());
        return user;
    }

    @GetMapping("/view")
    public List<UserView> getView() {
        return userViewRepository.findAll();
    }
}