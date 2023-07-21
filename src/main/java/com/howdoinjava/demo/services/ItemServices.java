package com.howdoinjava.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.howdoinjava.demo.models.Item;
import com.howdoinjava.demo.repositories.ItemRepository;

@Service
public class ItemServices {
    
    @Autowired
    ItemRepository itemRepository;

    public ArrayList<Item> getAllItems(){
        return (ArrayList<Item>) itemRepository.findAll();
    }

    public Item saveitem(Item i){
        return itemRepository.save(i);
    }
    
}
