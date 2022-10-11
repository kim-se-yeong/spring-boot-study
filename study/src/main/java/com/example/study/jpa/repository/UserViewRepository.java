package com.example.study.jpa.repository;

import com.example.study.jpa.dao.UserId;
import com.example.study.jpa.dao.UserView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserViewRepository extends ReadOnlyRepository<UserView, UserId> {

    List<UserView> findAll();
}
