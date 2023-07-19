package com.howdoinjava.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.howdoinjava.demo.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    
    
}
