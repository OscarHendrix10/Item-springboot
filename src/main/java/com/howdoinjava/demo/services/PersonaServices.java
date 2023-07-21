package com.howdoinjava.demo.services;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howdoinjava.demo.Handling.ItemNotFoundException;
import com.howdoinjava.demo.models.Persona;
import com.howdoinjava.demo.repositories.PersonaRepository;

@Service
public class PersonaServices {
    
    @Autowired
    PersonaRepository personaRepository;

    
    public ArrayList<Persona> getAllPersonas(){
        return (ArrayList<Persona>) personaRepository.findAll();
    }

    public Persona savePerson(Persona p){
        return personaRepository.save(p);
    }

    public Persona obtainId(Long id){
        return personaRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Persona updatePersona(Persona newPersona, Long id){
        return personaRepository.findById(id)
        .map(item -> {
            item.setNombre(newPersona.getNombre());
            return personaRepository.save(item);
          }).orElseGet(() -> {
            newPersona.setId(id);
            return personaRepository.save(newPersona);
          });
    }

    public boolean deletePersona(Long id){
        try {
            personaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
