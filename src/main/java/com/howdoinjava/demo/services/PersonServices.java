package com.howdoinjava.demo.services;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howdoinjava.demo.Handling.ItemNotFoundException;
import com.howdoinjava.demo.models.Person;
import com.howdoinjava.demo.repositories.PersonRepository;

@Service //indica que es un servicio
public class PersonServices {
    
    @Autowired //inyecta el repositorio
    PersonRepository personRepository;

    //devuelve todos lAs personas
    public ArrayList<Person> getAllPersonas(){
        //devuelve todos las personas de la base de datos
        return (ArrayList<Person>) personRepository.findAll();
    }

    //guarda una persona
    public Person savePerson(Person p){
        //guarda una persona en la base de datos
        return personRepository.save(p);
    }

    //obtiene una persona por id
    public Person obtainId(Long id){
        //devuelve una persona por id o lanza una excepcion
        //si no lo encuentra
        return personRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    //actualiza una persona
    public Person updatePersona(Person newPersona, Long id){
        //actualiza una persona o la crea si no existe
        //devuelve la persona actualizada
        //o lanza una excepcion si no lo encuentra
        return personRepository.findById(id)
        .map(item -> {
            item.setNombre(newPersona.getNombre());
            return personRepository.save(item);
          }).orElseGet(() -> {
            newPersona.setId(id);
            return personRepository.save(newPersona);
          });
    }
    //elimina una persona
    public boolean deletePersona(Long id){
        //elimina una persona por id
        //devuelve true si lo elimina
        //devuelve false si no lo elimina
        try {
            personRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
