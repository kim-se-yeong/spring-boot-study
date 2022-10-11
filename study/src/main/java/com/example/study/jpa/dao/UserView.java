package com.example.study.jpa.dao;

import lombok.ToString;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "user_view_with_account")
@Entity
@Immutable
@IdClass(UserId.class)
@ToString
public class UserView {

    @Id
    private Long id;

    @Id
    private String lastName;

    private String firstName;

    private LocalDateTime createdAt;

    private String number;

    private String bank;
}
