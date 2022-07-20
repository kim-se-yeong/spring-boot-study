package com.example.study.user;

import java.time.LocalDateTime;

import com.example.study.jpa.dao.User;
import com.example.study.jpa.repository.UserRepository;
import com.example.study.jpa.service.UserService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTests {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(false)
    void save() {
        // User user = User.builder()
        //     .id(0L)
        //     .firstName("seyeong")
        //     .lastName("kim")
        //     .build();

        User user = new User();
        user.setId(0L);
        user.setLastName("kim");
        user.setFirstName("seyeong");
        user.setCreatedAt(LocalDateTime.now());;
        
        User resUser = userRepository.save(user);

        // Assertions.assertThat(user).isSameAs(resUser);
    }
}
