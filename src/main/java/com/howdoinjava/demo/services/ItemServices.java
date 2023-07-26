package com.howdoinjava.demo.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.howdoinjava.demo.Handling.ItemNotFoundException;
import com.howdoinjava.demo.models.Item;
import com.howdoinjava.demo.repositories.ItemRepository;

@Service //indica que es un servicio
public class ItemServices {
    
    @Autowired //inyecta el repositorio
    ItemRepository itemRepository;

    public ArrayList<Item> getAllItems(){
        //devuelve todos los items
        return (ArrayList<Item>) itemRepository.findAll();
    }

    //guarda un item
    public Item saveitem(Item i){
        //guarda un item en la base de datos
        return itemRepository.save(i);
    }

    //obtiene un item por id
    public Item obtainId(Long id){
        //devuelve un item por id o lanza una excepcion
        //si no lo encuentra
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    //actualiza un item
    public Item updateItem(Item newItem, Long id){
        //actualiza un item o lo crea si no existe
        //devuelve el item actualizado
        //o lanza una excepcion si no lo encuentra
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

    //elimina un item
    public boolean deleteItem(Long id){
        //elimina un item por id
        //devuelve true si lo elimina
        //devuelve false si no lo elimina
        try {
            itemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    
}
