package com.example.project.repository;

import com.example.project.domain.Phone;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PhoneMemoryRepository implements PhoneRepository {

    private static long sequence = 0;
    private static Map<Long, Phone> store = new HashMap<>();

    @Override
    public Phone save(Phone phone) {
        phone.setId(sequence++);
        store.put(phone.getId(), phone);
        return phone;
    }

    @Override
    public Phone findById(Long id) {
        return store.get(id);
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public void updateById(Long id, Phone updateParam) {
        //updateParam 은 따로 DTO 로 관리
        Phone findPhone = findById(id);
        findPhone.setPrice(updateParam.getPrice());
        findPhone.setName(updateParam.getName());
        findPhone.setCompany(updateParam.getCompany());
    }

    @Override
    public List<Phone> findAll() {
        return new ArrayList<>(store.values());
//        return store.values()
//                .stream()
//                .collect(Collectors.toList());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
