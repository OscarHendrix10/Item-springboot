package com.howdoinjava.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howdoinjava.demo.Handling.ItemNotFoundException;
import com.howdoinjava.demo.models.Cuenta;
import com.howdoinjava.demo.repositories.CuentaRepository;

@Service
public class CuentaServices {
    @Autowired
    CuentaRepository cuentaRepository;

    public ArrayList<Cuenta> getAllCuentas(){
        return (ArrayList<Cuenta>) cuentaRepository.findAll();
    }

    public Cuenta saveCuenta(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    public Cuenta obtainId(Long id){
        return cuentaRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    
    public Cuenta updatePersona(Cuenta newCuenta, Long id){
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

    public boolean deleteCuenta(Long id){
        try {
            cuentaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
