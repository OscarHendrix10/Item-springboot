package com.howdoinjava.demo.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.howdoinjava.demo.models.Item;
import com.howdoinjava.demo.services.ItemServices;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/items")
public class ItemController {

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
    
}
