package com.howdoinjava.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.howdoinjava.demo.models.Customer;
import com.howdoinjava.demo.models.Person;
import com.howdoinjava.demo.repositories.CustomerRepository;

public class CustomersServicesTest {
    
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    private CustomersServices customersServices;
    private Customer customer, customer2;
    private Person person, person2;
     @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        person = new Person(1L, "oscar", "hendrix", 23);
        person2 = new Person(2L, "Cruz", "Cervantes", 24);
        customer = new Customer(1L, "oscaralan@email,com", "12345", person, true);
        customer2 = new Customer(2L, "cruzcervantes@email,com", "12345678", person2, true);
    }


    @Test
    void testGetAllCuentas() {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer); 
        customers.add(customer2);
        when(customerRepository.findAll()).thenReturn(customers);
        ArrayList<Customer> res = customersServices.getAllCuentas();
        assertEquals(2, res.size());
    }
    
    @Test
    void testObtainId() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer customer = customersServices.obtainId(1L);
        assertEquals(1L, customer.getId());
    }
    
    @Test
    void testSaveCuenta() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer res = customersServices.saveCuenta(customer);
        assertNotNull(res, "ningun campo puede estar vacio");
        assertEquals(res.getId(), 1L, res.getEmail());
    }
    
    @Test
    void testUpdatePersona() {
         customer = new Customer(1L, "oscaralanmod@email,com", "12345", person, true);
         when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
         customersServices.updatePersona(customer, 1L);
         Customer res = customersServices.obtainId(1L);
         assertEquals("oscaralanmod@email,com", res.getEmail());

    }

    @Test
    void testDeleteCuenta() {
        Long id = 1L;
        boolean res = customersServices.deleteCuenta(id);
        assertFalse(res);
    }
}
