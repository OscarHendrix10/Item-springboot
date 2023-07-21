package com.howdoinjava.demo.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.howdoinjava.demo.Handling.ItemNotFoundException;
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

    
    public Item obtainId(Long id){
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Item updateItem(Item newItem, Long id){
        return itemRepository.findById(id)
        .map(item -> {
          item.setName(newItem.getName());
          return itemRepository.save(item);
        })
        .orElseGet(() -> {
          newItem.setId(id);
          return itemRepository.save(newItem);
        });
    }

    public boolean deleteItem(Long id){
        try {
            itemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    
}
