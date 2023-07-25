package com.howdoinjava.demo.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("items")
@Api(tags = "Controlador de items", description = "CRUD de items en la base de datos de la aplicación")
public class ItemsController {
    
    @Autowired
    ItemServices itemServices;

    @GetMapping()
    @ApiOperation("Obtener todos los items")
    public ArrayList<Item> getAllItems(){
        return (ArrayList<Item>) itemServices.getAllItems();
    }

    @PostMapping()
    @ApiOperation("Guarda un item nuevo en la base de datos")
    public Item createNew(@Valid @RequestBody Item newItem) {
        return this.itemServices.saveitem(newItem);
    }

    @GetMapping(path = "{id}")
    @ApiOperation("Obtiene un item por su id en la base de datos")
    public Item obtainId(@PathVariable("id") Long id){
        return itemServices.obtainId(id);
    }

    @PutMapping(path = "{id}")
    @ApiOperation("Actualiza un item por su id en la base de datos")
    public Item updateOrCreate(@Valid @RequestBody Item newItem, @PathVariable Long id) {
        return this.itemServices.updateItem(newItem, id);
    }
        

    @DeleteMapping(path = "{id}")
    @ApiOperation("Elimina un item por su id en la base de datos")
    public String deleteItem(@PathVariable("id") Long id){
        boolean ok = this.itemServices.deleteItem(id);
        if(ok){
            return "Se eliminó el item con id " + id;
        }else{
            return "No se pudo eliminar el item con id " + id;
        }
    }
}
