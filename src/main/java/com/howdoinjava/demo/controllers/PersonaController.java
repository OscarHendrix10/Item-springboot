package com.howdoinjava.demo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.howdoinjava.demo.models.Persona;
import com.howdoinjava.demo.services.PersonaServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("persona")
public class PersonaController {
    @Autowired
    PersonaServices personaServices;

    @GetMapping()
    public ArrayList<Persona> getAllItems(){
        return (ArrayList<Persona>) personaServices.getAllPersonas();
    }

    @PostMapping()
    public Persona createNew(@Valid @RequestBody Persona newPersona) {
        return this.personaServices.savePersona(newPersona);
    }

    @GetMapping(path = "{id}")
    public Persona obtainId(@PathVariable("id") Long id){
        return personaServices.obtainId(id);
    }

    @PutMapping(path = "{id}")
    public Persona updateOrCreate(@Valid @RequestBody Persona newPersona, @PathVariable Long id) {
        return this.personaServices.updatePersona(newPersona, id);
    }

    @DeleteMapping(path = "{id}")
    public String deleteItem(@PathVariable("id") Long id){
        boolean ok = this.personaServices.deletePersona(id);
        if(ok){
            return "Se eliminó el item con id " + id;
        }else{
            return "No se pudo eliminar el item con id " + id;
        }
    }

}
