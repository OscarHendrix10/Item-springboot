package com.howdoinjava.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.howdoinjava.demo.models.Person;
import com.howdoinjava.demo.repositories.PersonRepository;

public class PersonServicesTest {
    
    @Mock
    PersonRepository personRepository;

    @InjectMocks
    private PersonServices personServices;
    private Person person, person2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        person = new Person(1L, "oscar", "hendrix", 23);
        person2 = new Person(2L, "Cruz", "Cervantes", 24);
    }
    
    @Test
    void testGetAllPersonas() {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(person2);
        when(personRepository.findAll()).thenReturn(persons);
        ArrayList<Person> res = personServices.getAllPersonas();
        assertEquals(2, res.size());

    }

    
    @Test
    void testObtainId() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        Person person = personServices.obtainId(1L);
        assertEquals(1L, person.getId());
    }

    @Test
    void testSavePerson() {
        when(personRepository.save(any(Person.class))).thenReturn(person);
        Person res = personServices.savePerson(person);
        assertNotNull(res, "ningun campo puede estar vacio");
        assertEquals(res.getId(), 1L, res.getNombre());
    }
    
    @Test
    void testUpdatePersona() {
        Person person = new Person(1L, "Oscar Alan", "Hendrix" ,19);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        personServices.updatePersona(person, 1L);
        Person res = personServices.obtainId(1L);
        assertEquals("Oscar Alan", res.getNombre());
        
    }


    @Test
    void testDeletePersona() {
        Long id = 1L;
        personServices.deletePersona(id);
        verify(personRepository, times(1)).deleteById(id);
    }
}
