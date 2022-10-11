package com.example.study.user.controller;

import com.example.study.api.dto.User;
import com.example.study.api.dto.UserName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTests {

    @Test
    @DisplayName("postForEntity 함수를 사용하여 호출")
    void callUserSaveAPIUsingPostForEntity() {
        final String url = "http://127.0.0.1:8080/api/v1/user/save";
        RestTemplate restTemplate = new RestTemplate();
        User user = User.builder()
                .age(26)
                .userName(new UserName("세영", "김"))
                .build();

        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.postForEntity(url, request, User.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("exchange 함수를 사용하여 호출")
    void callUserSaveAPIUsingExchange() {
        final String url = "http://127.0.0.1:8080/api/v1/user/save";
        RestTemplate restTemplate = new RestTemplate();
        User user = User.builder()
                .age(26)
                .userName(new UserName("세영", "김"))
                .build();

        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.POST, request, User.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
