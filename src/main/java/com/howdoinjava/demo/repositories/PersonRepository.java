package com.howdoinjava.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.howdoinjava.demo.models.Person;

@Repository //es un repositorio
//jpa es un repositorio que se encarga de hacer las consultas a la base de datos
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
