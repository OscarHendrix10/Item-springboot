package com.howdoinjava.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.howdoinjava.demo.models.Item;
import com.howdoinjava.demo.repositories.ItemRepository;

public class ItemServicesTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    private ItemServices itemServices;
    private Item item, item2 ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        item = new Item(1L, "Item 1");
        item2 = new Item(2L, "Item 2");
    }


    @Test
    void testGetAllItems() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        items.add(item2);
        when(itemRepository.findAll()).thenReturn(items);
        ArrayList<Item> res = itemServices.getAllItems();
        assertEquals(2, res.size());
    }


    @Test
    void testObtainId() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        Item res = itemServices.obtainId(1L);
        assertEquals(1L, 1L, res.getName());
    }

    @Test
    void testSaveitem() {
        when(itemRepository.save(any(Item.class))).thenReturn(item);
        Item res = itemServices.saveitem(item);

        assertNotNull(res);
        assertEquals(res.getId(), 1L, res.getName());
    }

    @Test
    void testUpdateItem() {
        Item item = new Item();
        item.setId(1L);
        item.setName("item 1 mod");
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        itemServices.updateItem(item, 1L);
        Item res = itemServices.obtainId(1L);
        assertEquals("item 1 mod", res.getName());
    }

    @Test
    void testDeleteItem() {
         Long id = 1L;
        itemServices.deleteItem(id);
        verify(itemRepository, times(1)).deleteById(id);
    }
}
