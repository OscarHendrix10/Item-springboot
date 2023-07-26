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


@RestController //indica que es un controlador rest
@RequestMapping("items") //indica la ruta de la api rest
//indica que es un controlador de items
@Api(tags = "Controlador de items", description = "CRUD de items en la base de datos de la aplicación")
public class ItemsController {
    
    @Autowired //inyecta el servicio
    ItemServices itemServices;

    @GetMapping() //indica que es un metodo get
    @ApiOperation("Obtener todos los items") //indica que es un metodo de la api
    //obtiene todos los items
    public ArrayList<Item> getAllItems(){
        //devuelve todos los items de la base de datos
        return (ArrayList<Item>) itemServices.getAllItems();
    }

    @PostMapping() //indica que es un metodo post
    @ApiOperation("Guarda un item nuevo en la base de datos") //indica que es un metodo de la api
    //guarda un item nuevo en la base de datos
    public Item createNew(@Valid @RequestBody Item newItem) {
        //guarda un item en la base de datos
        return this.itemServices.saveitem(newItem);
    }

    @GetMapping(path = "{id}") //indica que es un metodo get
    @ApiOperation("Obtiene un item por su id en la base de datos") //indica que es un metodo de la api
    //obtiene un item por id de la base de datos
    public Item obtainId(@PathVariable("id") Long id){
        //devuelve un item por id o lanza una excepcion
        return itemServices.obtainId(id);
    }

    @PutMapping(path = "{id}") //indica que es un metodo put
    @ApiOperation("Actualiza un item por su id en la base de datos") //indica que es un metodo de la api
    public Item updateOrCreate(@Valid @RequestBody Item newItem, @PathVariable Long id) {
        //actualiza un item o lo crea si no existe
        return this.itemServices.updateItem(newItem, id);
    }
        
    @DeleteMapping(path = "{id}") //indica que es un metodo delete
    @ApiOperation("Elimina un item por su id en la base de datos") //indica que es un metodo de la api
    public String deleteItem(@PathVariable("id") Long id){
        //elimina un item por id
        //devuelve true si lo elimina
        //devuelve false si no lo elimina
        boolean ok = this.itemServices.deleteItem(id);
        if(ok){
            return "Se eliminó el item con id " + id;
        }else{
            return "No se pudo eliminar el item con id " + id;
        }
    }
}
