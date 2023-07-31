package com.howdoinjava.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howdoinjava.demo.Handling.ItemNotFoundException;
import com.howdoinjava.demo.models.Cuenta;
import com.howdoinjava.demo.repositories.CuentaRepository;

@Service //indica que es un servicio
public class CuentaServices {
    @Autowired //inyecta el repositorio
    CuentaRepository cuentaRepository;

    //devuelve todos las cuentas
    public ArrayList<Cuenta> getAllCuentas(){
        //devuelve todos las cuentas de la base de datos
        return (ArrayList<Cuenta>) cuentaRepository.findAll();
    }

    //guarda una cuenta
    public Cuenta saveCuenta(Cuenta cuenta){
        //guarda una cuenta en la base de datos
        return cuentaRepository.save(cuenta);
    }

    //obtiene una cuenta por id
    public Cuenta obtainId(Long id){
        //devuelve una cuenta por id o lanza una excepcion
        //si no lo encuentra
        return cuentaRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    //actualiza una cuenta
    public Cuenta updatePersona(Cuenta newCuenta, Long id){
        //actualiza una cuenta o la crea si no existe
        //devuelve la cuenta actualizada
        //o lanza una excepcion si no lo encuentra
        return cuentaRepository.findById(id)
        .map(cuenta -> {
            cuenta.setEmail(newCuenta.getEmail());
            cuenta.setPassword(newCuenta.getPassword());
            return cuentaRepository.save(cuenta);
          }).orElseGet(() -> {
            newCuenta.setId(id);
            return cuentaRepository.save(newCuenta);
          });
    }
    //elimina una cuenta
    public boolean deleteCuenta(Long id){
        //elimina una cuenta por id
        //devuelve true si lo elimina
        //devuelve false si no lo elimina
        try {
           Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
           cuenta.setStatus(false);
           cuentaRepository.save(cuenta);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
