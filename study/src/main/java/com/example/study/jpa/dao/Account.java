package com.example.study.jpa.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    private String bank;

    @Id
    private String number;
}