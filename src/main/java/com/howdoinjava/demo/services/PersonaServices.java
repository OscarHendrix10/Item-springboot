package com.howdoinjava.demo.services;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howdoinjava.demo.Handling.ItemNotFoundException;
import com.howdoinjava.demo.models.Persona;
import com.howdoinjava.demo.repositories.PersonaRepository;

@Service //indica que es un servicio
public class PersonaServices {
    
    @Autowired //inyecta el repositorio
    PersonaRepository personaRepository;

    //devuelve todos lAs personas
    public ArrayList<Persona> getAllPersonas(){
        //devuelve todos las personas de la base de datos
        return (ArrayList<Persona>) personaRepository.findAll();
    }

    //guarda una persona
    public Persona savePerson(Persona p){
        //guarda una persona en la base de datos
        return personaRepository.save(p);
    }

    //obtiene una persona por id
    public Persona obtainId(Long id){
        //devuelve una persona por id o lanza una excepcion
        //si no lo encuentra
        return personaRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    //actualiza una persona
    public Persona updatePersona(Persona newPersona, Long id){
        //actualiza una persona o la crea si no existe
        //devuelve la persona actualizada
        //o lanza una excepcion si no lo encuentra
        return personaRepository.findById(id)
        .map(item -> {
            item.setNombre(newPersona.getNombre());
            return personaRepository.save(item);
          }).orElseGet(() -> {
            newPersona.setId(id);
            return personaRepository.save(newPersona);
          });
    }
    //elimina una persona
    public boolean deletePersona(Long id){
        //elimina una persona por id
        //devuelve true si lo elimina
        //devuelve false si no lo elimina
        try {
            personaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
