package com.howdoinjava.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.howdoinjava.demo.models.Cuenta;
import com.howdoinjava.demo.models.Item;
import com.howdoinjava.demo.models.Persona;
import com.howdoinjava.demo.services.CuentaServices;
import com.howdoinjava.demo.services.ItemServices;
import com.howdoinjava.demo.services.PersonaServices;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

  @Autowired
  private ItemServices itemServices;
  @Autowired
  private PersonaServices personaServices;
  @Autowired
  private CuentaServices cuentaServices;

  @Override
  public void run(String... args) throws Exception{
    itemServices.saveitem(new Item(null, "Item 1"));
    itemServices.saveitem(new Item(null, "Item 2"));

    Persona persona1 = new Persona(null, "Oscar", "Hendrix" ,19);
    personaServices.savePerson(persona1);
    Persona persona2 = new Persona(null, "Cruz", "Aranda" ,21);
    personaServices.savePerson(persona2);


    cuentaServices.saveCuenta(new Cuenta(null, "Oscar@gmail.com", "123456", persona1));
    cuentaServices.saveCuenta(new Cuenta(null, "Cruz@gmail.com", "123456-10", persona2));

  }

  @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.howdoinjava.demo.controllers")) // Reemplaza por el paquete de tus controladores
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("APIs de Entrenamiento")
                .description("Documentación de la API de entrenamiento de Spring Boot")
                .version("1.0.0")
                .build();
    }

}
