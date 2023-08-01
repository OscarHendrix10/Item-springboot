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

import com.howdoinjava.demo.models.Customer;
import com.howdoinjava.demo.services.CustomersServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController //indica que es un controlador rest
@RequestMapping("customers") //indica la ruta de la api rest
//indica que es un controlador de cuenta
@Api(tags = "Controlador de cuenta", description = "CRUD de Cuenta en la base de datos de la aplicación")
public class CustomerController {
    @Autowired //inyecta el servicio
    CustomersServices customersServices;

    @GetMapping("getAll") //indica que es un metodo get
    @ApiOperation("Obtener todos los items") //indica que es un metodo de la api
    public ArrayList<Customer> getAllcuenta(){
        //devuelve todos los items de la base de datos
        return (ArrayList<Customer>) customersServices.getAllCuentas();
    }

    @PostMapping("add") //indica que es un metodo post
    @ApiOperation("Guarda una cuenta nueva en la base de datos") //indica que es un metodo de la api
    public Customer createNew(@Valid @RequestBody Customer newCuenta) {
        //guarda un ccuenta en la base de datos
        return this.customersServices.saveCuenta(newCuenta);
    }

    @GetMapping(path = "get/{id}")//indica que es un metodo get
    @ApiOperation("Obtiene una cuenta por su id en la base de datos") //indica que es un metodo de la api
    public Customer obtainId(@PathVariable("id") Long id){
        //devuelve una cuenta por id o lanza una excepcion
        return customersServices.obtainId(id);
    }

    @PutMapping(path = "edit/{id}")//indica que es un metodo put
    @ApiOperation("Actualiza una cuenta por su id en la base de datos") //indica que es un metodo de la api
    public Customer updateOrCreate(@Valid @RequestBody Customer newCuenta, @PathVariable Long id) {
        //actualiza un item o lo crea si no existe
        return this.customersServices.updatePersona(newCuenta, id);
    }

    @DeleteMapping(path = "disable/{id}")//indica que es un metodo delete
    @ApiOperation("deshabilita una cuenta por su id en la base de datos") //indica que es un metodo de la api
    public String deletecuenta(@PathVariable("id") Long id){
        //elimina una cuenta por id
        //devuelve un mensaje de exito o fracaso
        //si se elimina o no
        boolean ok = this.customersServices.deleteCuenta(id);
        if(ok){
            return "Se deshabilito el customer con id " + id;
        }else{
            return "No se pudo deshabilitar el customer con id " + id;
        }
    }

}
