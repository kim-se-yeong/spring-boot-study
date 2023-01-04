package com.example.project.repository;

import com.example.project.domain.Phone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PhoneMemoryRepositoryTest {

    private PhoneRepository phoneRepository = new PhoneMemoryRepository();

    @AfterEach
    void afterEach() {
        phoneRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Phone phone = new Phone("iphone-mini 12", "apple", 1000000);

        //when
        Phone save = phoneRepository.save(phone);

        //then
        Phone find = phoneRepository.findById(phone.getId());
        assertThat(find).isEqualTo(save);
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateById() {
        //given
        Phone phone = new Phone("iphone-mini 12", "apple", 1000000);
        Phone save = phoneRepository.save(phone);
        Long id = save.getId();

        //when
        Phone updateParam = new Phone("z-flip", "samsung", 1200000);
        phoneRepository.updateById(id, updateParam);

        //then
        Phone find = phoneRepository.findById(id);
        assertThat(find.getName()).isEqualTo(updateParam.getName());
        assertThat(find.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(find.getCompany()).isEqualTo(updateParam.getCompany());
    }

    @Test
    void findAll() {
        //given
        Phone phone1 = new Phone("iphone-mini 12", "apple", 1000000);
        Phone phone2 = new Phone("z-flip", "samsung", 1200000);

        //when
        phoneRepository.save(phone1);
        phoneRepository.save(phone2);

        //then
        List<Phone> result = phoneRepository.findAll();
        assertThat(result).contains(phone1, phone2);
    }
}