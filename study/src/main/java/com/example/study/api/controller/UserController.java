package com.example.study.api.controller;

import com.example.study.api.dto.User;

import com.example.study.jpa.dao.UserView;
import com.example.study.jpa.repository.UserViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserViewRepository userViewRepository;

    @GetMapping("/get")
    public User get(@RequestBody User user) {
        log.info("user name {}, {}", user.getUserName().getLastName(), user.getUserName().getFirstName());
        return null;
    }

    @GetMapping("/get")
    public List<UserView> getView() {
        return userViewRepository.findAll();
    }
}