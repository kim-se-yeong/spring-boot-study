package com.example.study.user;


import com.example.study.jpa.dao.Account;
import com.example.study.jpa.dao.User;
import com.example.study.jpa.dao.UserView;
import com.example.study.jpa.repository.UserRepository;
import com.example.study.jpa.repository.UserViewRepository;
import com.example.study.jpa.service.UserService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTests {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserViewRepository userViewRepository;

    @Test
    @Rollback(false)
    @DisplayName("user 생성하기")
    void save() {

        /**
         * save 테스트 이전에 한번 읽기!
         * save 구현체를 한번 확인해보자.
         * save 메서드에서 entityInformation.isNew() 실행하게 되는데,
         * isNew 메서드는 idType 이 primitive 가 아니라면 'id == null' 이라는 boolean 값을 리턴한다.
         * user 객체의 idType는 primitive 가 아니지만, id 또한 null 이기 때문에 true를 리턴한다.
         *
         * isNew 메서드의 리턴값이 true 일 경우, em.persist() 이후 save 대상의 엔티티를 그대로 리턴하지만
         * false 인 경우, em.merge() 한 엔티티를 리턴한다.
         */
        User user = new User();
        user.setName("김세영");
        user.setCreatedAt(LocalDateTime.now());;
        
        User resUser = userRepository.save(user);
        assertThat(user).isEqualTo(resUser);
    }

    @Test
    void findMember() {

        User user = userRepository.findByName("김세영");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Account> accountList = user.getAccountList();
        accountList.forEach(account -> System.out.println(account.getNumber()));
    }

    @Test
    @DisplayName("사용자 전체 조회 시 N + 1 문제 발생 확인 - 즉시로딩")
    void findAllWithEagerLoading() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> System.out.println(user.getId()));
    }

    @Test
    @DisplayName("사용자 전체 조회 시 N + 1 문제 발생 확인 - 지연로딩")
    void findAllWithLazyLoading() {
        List<User> users = userRepository.findAll();

        try {
            System.out.println("1초 쉬겠습니다..");
            Thread.sleep(1000);
            System.out.println("1초 다 쉬었습니다..");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<Account> accountList = users.get(0).getAccountList();
        accountList.forEach(account -> System.out.println(account.getNumber()));
    }

    @Test
    @DisplayName("N + 1 문제 해결 - fetchJoin")
    void findAllWithFetchJoin() {

        Set<User> users = userRepository.findAllWithFetchJoin();
        users.forEach(user -> {
            user.getAccountList().forEach(account -> {
                System.out.println(user.getName() + ", " + account.getNumber());
            });
        });
    }

    @Test
    @DisplayName("N + 1 문제 해결 - fetchJoin + distinct")
    void findAllWithFetchJoinWithoutDuplicate() {

        List<User> users = userRepository.findAllWithFetchJoinWithoutDuplicate();
        users.forEach(user -> {
            user.getAccountList().forEach(account -> {
                System.out.println(user.getName() + ", " + account.getNumber());
            });
        });
    }

    @Test
    @DisplayName("View 테스트")
    void findViewEntity() {
        List<UserView> all = userViewRepository.findAll();
        for (UserView user : all) {
            System.out.println("user.toString() = " + user.toString());
        }
    }
}
