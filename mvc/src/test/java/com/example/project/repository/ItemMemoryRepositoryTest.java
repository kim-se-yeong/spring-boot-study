package com.example.project.repository;

import com.example.project.domain.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemMemoryRepositoryTest {

    private ItemRepository itemRepository = new ItemMemoryRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item phone = new Item("iphone-mini 12", 1000000, 1);

        //when
        Item save = itemRepository.save(phone);

        //then
        Item find = itemRepository.findById(phone.getId());
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
        Item phone = new Item("iphone-mini 12",1000000, 1);
        Item save = itemRepository.save(phone);
        Long id = save.getId();

        //when
        Item updateParam = new Item("z-flip", 1200000, 2);
        itemRepository.updateById(id, updateParam);

        //then
        Item find = itemRepository.findById(id);
        assertThat(find.getName()).isEqualTo(updateParam.getName());
        assertThat(find.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(find.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("iphone-mini 12",1000000, 1);
        Item item2 = new Item("z-flip", 1200000, 2);

        //when
        itemRepository.save(item1);
        itemRepository.save(item2);

        //then
        List<Item> result = itemRepository.findAll();
        assertThat(result).contains(item1, item2);
    }
}