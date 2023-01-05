package com.example.project.repository;

import com.example.project.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemMemoryRepository implements ItemRepository {

    private static long sequence = 0;
    private static Map<Long, Item> store = new HashMap<>();

    @Override
    public Item save(Item item) {
        item.setId(sequence++);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(Long id) {
        return store.get(id);
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public void updateById(Long id, Item updateParam) {
        //updateParam 은 따로 DTO 로 관리
        Item findItem = findById(id);
        findItem.setPrice(updateParam.getPrice());
        findItem.setName(updateParam.getName());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public List<Item> findAll() {
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
