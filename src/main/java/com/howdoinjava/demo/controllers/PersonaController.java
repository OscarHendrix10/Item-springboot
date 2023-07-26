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
import com.howdoinjava.demo.models.Persona;
import com.howdoinjava.demo.services.PersonaServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController //indica que es un controlador rest
@RequestMapping("persona") //indica la ruta de la api rest
//indica que es un controlador de persona
@Api(tags = "Controlador de Persona", description = "CRUD de las personas en la base de datos de la aplicación")
public class PersonaController {
    @Autowired //inyecta el servicio
    PersonaServices personaServices;

    @GetMapping() //indica que es un metodo get
    @ApiOperation("Obtener todos las personas") //indica que es un metodo de la api
    //obtiene todos las personas
    public ArrayList<Persona> getAllpPersonas(){
        //devuelve todos las personas de la base de datos
        return (ArrayList<Persona>) personaServices.getAllPersonas();
    }

    @PostMapping() //indica que es un metodo post
    @ApiOperation("Guarda una persona nueva en la base de datos") //indica que es un metodo de la api
    //guarda una persona nueva en la base de datos
    public Persona createNew(@Valid @RequestBody Persona newPersona) {
        //guarda una persona en la base de datos
        return this.personaServices.savePerson(newPersona);
    }

    @GetMapping(path = "{id}") //indica que es un metodo get
    @ApiOperation("Obtiene una persona por su id en la base de datos") //indica que es un metodo de la api
    public Persona obtainId(@PathVariable("id") Long id){
        //devuelve una persona por id o lanza una excepcion
        return personaServices.obtainId(id);
    }

    @PutMapping(path = "{id}") // indica que es un metodo put
    @ApiOperation("Actualiza una persona por su id en la base de datos") //indica que es un metodo de la api
    public Persona updateOrCreate(@Valid @RequestBody Persona newPersona, @PathVariable Long id) {
        //actualiza una persona o la crea si no existe
        return this.personaServices.updatePersona(newPersona, id);
    }

    @DeleteMapping(path = "{id}") //indica que es un metodo delete
    @ApiOperation("Elimina una persona por su id en la base de datos") //indica que es un metodo de la api
    public String deletePersona(@PathVariable("id") Long id){
        //elimina una persona por id
        //devuelve un mensaje de confirmacion
        //o un mensaje de error
        boolean ok = this.personaServices.deletePersona(id);
        if(ok){
            return "Se eliminó el persona con id " + id;
        }else{
            return "No se pudo eliminar el persona con id " + id;
        }
    }

}
