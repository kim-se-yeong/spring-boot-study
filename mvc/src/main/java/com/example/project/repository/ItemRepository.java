package com.example.project.repository;

import com.example.project.domain.Item;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    Item findById(Long id);

    void deleteById(Long id);

    void updateById(Long id, Item updateParam);

    List<Item> findAll();

    void clearStore();
}
