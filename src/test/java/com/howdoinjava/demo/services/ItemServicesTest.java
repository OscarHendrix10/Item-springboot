package com.howdoinjava.demo.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import com.howdoinjava.demo.models.Item;
import com.howdoinjava.demo.repositories.ItemRepository;

public class ItemServicesTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServices itemServices;
    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item();
        item.setId(1L);
        item.setName("item1");
    }




    @Test
    void testDeleteItem() {

    }


    /**
     * 
     */
    @Test
    void testGetAllItems() {
        ArrayList <Item> items = new ArrayList<Item>();
        items.add(item);
        when(itemRepository.findAll()).thenReturn(items);
        assertNotNull(itemServices.getAllItems());
    }
    

    @Test
    void testObtainId() {

    }

    @Test
    void testSaveitem() {

    }

    @Test
    void testUpdateItem() {

    }
}
