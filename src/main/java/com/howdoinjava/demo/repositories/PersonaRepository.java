package com.howdoinjava.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.howdoinjava.demo.models.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
}
