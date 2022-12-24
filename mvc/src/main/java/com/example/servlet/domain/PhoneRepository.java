package com.example.servlet.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneRepository {

    private static Map<Long, Phone> store = new HashMap<>();
    private static long sequence = 0L;

    private static final PhoneRepository instance = new PhoneRepository();

    public static PhoneRepository getInstance() {
        return instance;
    }
    private PhoneRepository() {

    }

    public Phone save(Phone phone) {
        phone.setId(++sequence);
        store.put(phone.getId(), phone);
        return phone;
    }

    public Phone findById(Long id) {
        return store.get(id);
    }

    public List<Phone> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
