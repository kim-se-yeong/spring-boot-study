package com.example.project.repository;

import com.example.project.domain.Phone;

import java.util.List;

public interface PhoneRepository {

    Phone save(Phone phone);

    Phone findById(Long id);

    void deleteById(Long id);

    void updateById(Long id, Phone updateParam);

    List<Phone> findAll();

    void clearStore();
}
