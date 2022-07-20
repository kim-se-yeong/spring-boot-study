package com.example.study.jpa.dao;

import javax.persistence.ConstraintMode;
import javax.persistence.ForeignKey;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Account {
    
    @ManyToOne
    // @JoinColumns(value = {
    //     @JoinColumn(name = "user_id"),
    //     @JoinColumn(name = "user_last_name")
    // })
    @JoinColumn(name = "created_at", referencedColumnName = "createdAt")
    private User user;

    private String bank;

    @Id
    private String number;
}