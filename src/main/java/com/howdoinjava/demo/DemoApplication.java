package com.howdoinjava.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.howdoinjava.demo.models.Item;
import com.howdoinjava.demo.models.Persona;
import com.howdoinjava.demo.services.ItemServices;
import com.howdoinjava.demo.services.PersonaServices;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

  @Autowired
  private ItemServices itemServices;
  private PersonaServices personaServices;

  @Override
  public void run(String... args) throws Exception{
    itemServices.saveitem(new Item(null, "Item 1"));
    itemServices.saveitem(new Item(null, "Item 2"));

    personaServices.savePerson(new Persona(null, "Oscar", "Hendrix" ,19));
    personaServices.savePerson(new Persona(null, "Cruz", "Aranda" ,39));
    personaServices.savePerson(new Persona(null, "Ciclali", "Martinez" ,29));

  }

}
