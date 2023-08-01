package com.howdoinjava.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howdoinjava.demo.Handling.ItemNotFoundException;
import com.howdoinjava.demo.models.Customer;
import com.howdoinjava.demo.repositories.CustomerRepository;

@Service //indica que es un servicio
public class CustomersServices {
    @Autowired //inyecta el repositorio
    CustomerRepository customerRepository;

    //devuelve todos las cuentas
    public ArrayList<Customer> getAllCuentas(){
        //devuelve todos las cuentas de la base de datos
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    //guarda una cuenta
    public Customer saveCuenta(Customer cuenta){
        //guarda una cuenta en la base de datos
        return customerRepository.save(cuenta);
    }

    //obtiene una cuenta por id
    public Customer obtainId(Long id){
        //devuelve una cuenta por id o lanza una excepcion
        //si no lo encuentra
        return customerRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    //actualiza una cuenta
    public Customer updatePersona(Customer newCuenta, Long id){
        //actualiza una cuenta o la crea si no existe
        //devuelve la cuenta actualizada
        //o lanza una excepcion si no lo encuentra
        return customerRepository.findById(id)
        .map(cuenta -> {
            cuenta.setEmail(newCuenta.getEmail());
            cuenta.setPassword(newCuenta.getPassword());
            return customerRepository.save(cuenta);
          }).orElseGet(() -> {
            newCuenta.setId(id);
            return customerRepository.save(newCuenta);
          });
    }
    //elimina una cuenta
    public boolean deleteCuenta(Long id){
        //elimina una cuenta por id
        //devuelve true si lo elimina
        //devuelve false si no lo elimina
        try {
           Customer cuenta = customerRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
           cuenta.setStatus(false);
           customerRepository.save(cuenta);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
