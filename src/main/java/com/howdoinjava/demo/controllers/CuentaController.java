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

import com.howdoinjava.demo.models.Cuenta;
import com.howdoinjava.demo.services.CuentaServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController //indica que es un controlador rest
@RequestMapping("cuenta") //indica la ruta de la api rest
//indica que es un controlador de cuenta
@Api(tags = "Controlador de cuenta", description = "CRUD de Cuenta en la base de datos de la aplicación")
public class CuentaController {
    @Autowired //inyecta el servicio
    CuentaServices cuentaServices;

    @GetMapping() //indica que es un metodo get
    @ApiOperation("Obtener todos los items") //indica que es un metodo de la api
    public ArrayList<Cuenta> getAllcuenta(){
        //devuelve todos los items de la base de datos
        return (ArrayList<Cuenta>) cuentaServices.getAllCuentas();
    }

    @PostMapping() //indica que es un metodo post
    @ApiOperation("Guarda una cuenta nueva en la base de datos") //indica que es un metodo de la api
    public Cuenta createNew(@Valid @RequestBody Cuenta newCuenta) {
        //guarda un ccuenta en la base de datos
        return this.cuentaServices.saveCuenta(newCuenta);
    }

    @GetMapping(path = "{id}")//indica que es un metodo get
    @ApiOperation("Obtiene una cuenta por su id en la base de datos") //indica que es un metodo de la api
    public Cuenta obtainId(@PathVariable("id") Long id){
        //devuelve una cuenta por id o lanza una excepcion
        return cuentaServices.obtainId(id);
    }

    @PutMapping(path = "{id}")//indica que es un metodo put
    @ApiOperation("Actualiza una cuenta por su id en la base de datos") //indica que es un metodo de la api
    public Cuenta updateOrCreate(@Valid @RequestBody Cuenta newCuenta, @PathVariable Long id) {
        //actualiza un item o lo crea si no existe
        return this.cuentaServices.updatePersona(newCuenta, id);
    }

    @DeleteMapping(path = "{id}")//indica que es un metodo delete
    @ApiOperation("Elimina una cuenta por su id en la base de datos") //indica que es un metodo de la api
    public String deletecuenta(@PathVariable("id") Long id){
        //elimina una cuenta por id
        //devuelve un mensaje de exito o fracaso
        //si se elimina o no
        boolean ok = this.cuentaServices.deleteCuenta(id);
        if(ok){
            return "Se eliminó el cuenta con id " + id;
        }else{
            return "No se pudo eliminar el cuenta con id " + id;
        }
    }

}
