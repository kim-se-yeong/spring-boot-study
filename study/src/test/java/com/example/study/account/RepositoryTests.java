package com.example.study.account;

import com.example.study.jpa.dao.Account;
import com.example.study.jpa.repository.AccountRepository;
import com.example.study.jpa.repository.UserRepository;

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
    
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Rollback(false)
    void save() {

        Account account = new Account();
        account.setBank("shinhan");
        account.setNumber("110-123-456789");
        account.setUser(userRepository.findByFirstName("sein"));

        accountRepository.save(account);
    }
}
