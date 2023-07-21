package com.howdoinjava.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.howdoinjava.demo.models.Item;

@Repository
public interface PersonaRepository extends JpaRepository<Item, Long> {
    
}