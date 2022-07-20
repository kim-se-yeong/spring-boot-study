package com.example.study.jpa.dao;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table
@Entity
@Setter
// @Builder
// @AllArgsConstructor
@IdClass(UserId.class)
public class User implements Serializable {

    @Id
    private Long id;

    @Id
    private String lastName;

    @Column(unique = true)
    private String firstName;

    private LocalDateTime createdAt;
}