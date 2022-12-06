package com.example.study.jpa.repository;

import com.example.study.jpa.dao.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    User findByName(String name);

    @Query("SELECT user FROM User user JOIN FETCH user.accountList")
    List<User> findAllWithFetchJoin();

    //중복 제거
    @Query("SELECT DISTINCT user FROM User user JOIN FETCH user.accountList")
    List<User> findAllWithFetchJoinWithoutDuplicate();
}
