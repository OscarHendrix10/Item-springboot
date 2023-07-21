package com.howdoinjava.demo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howdoinjava.demo.models.Item;
import com.howdoinjava.demo.services.ItemServices;

import jakarta.validation.Valid;


@RestController
@RequestMapping("items")
public class ItemsController {
    
    @Autowired
    ItemServices itemServices;

    @GetMapping()
    public ArrayList<Item> getAllItems(){
        return (ArrayList<Item>) itemServices.getAllItems();
    }

    @PostMapping()
    public Item createNew(@Valid @RequestBody Item newItem) {
        return this.itemServices.saveitem(newItem);
    }

    @GetMapping(path = "{id}")
    public Item obtainId(@PathVariable("id") Long id){
        return itemServices.obtainId(id);
    }

    @PutMapping(path = "{id}")
    public Item updateOrCreate(@Valid @RequestBody Item newItem, @PathVariable Long id) {
        return this.itemServices.updateItem(newItem, id);
    }
        

    @DeleteMapping(path = "{id}")
    public String deleteItem(@PathVariable("id") Long id){
        boolean ok = this.itemServices.deleteItem(id);
        if(ok){
            return "Se eliminó el item con id " + id;
        }else{
            return "No se pudo eliminar el item con id " + id;
        }
    }
}
