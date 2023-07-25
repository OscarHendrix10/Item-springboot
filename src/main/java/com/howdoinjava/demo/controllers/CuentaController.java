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

@RestController
@RequestMapping("cuenta")
public class CuentaController {
    @Autowired
    CuentaServices cuentaServices;

    @GetMapping()
    public ArrayList<Cuenta> getAllItems(){
        return (ArrayList<Cuenta>) cuentaServices.getAllCuentas();
    }

    @PostMapping()
    public Cuenta createNew(@Valid @RequestBody Cuenta newCuenta) {
        return this.cuentaServices.saveCuenta(newCuenta);
    }

    @GetMapping(path = "{id}")
    public Cuenta obtainId(@PathVariable("id") Long id){
        return cuentaServices.obtainId(id);
    }

    @PutMapping(path = "{id}")
    public Cuenta updateOrCreate(@Valid @RequestBody Cuenta newCuenta, @PathVariable Long id) {
        return this.cuentaServices.updatePersona(newCuenta, id);
    }

     @DeleteMapping(path = "{id}")
    public String deleteItem(@PathVariable("id") Long id){
        boolean ok = this.cuentaServices.deleteCuenta(id);
        if(ok){
            return "Se eliminó el cuenta con id " + id;
        }else{
            return "No se pudo eliminar el cuenta con id " + id;
        }
    }

}
