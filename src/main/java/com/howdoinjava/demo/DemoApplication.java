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

@EnableSwagger2 //habilita swagger
@SpringBootApplication //indica que es una aplicacion spring boot
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args); //inicia la aplicacion
	}

  @Autowired //inyecta el servicio
  private ItemServices itemServices;
  @Autowired //inyecta el servicio
  private PersonaServices personaServices;
  @Autowired //inyecta el servicio
  private CuentaServices cuentaServices;

  @Override //indica que se va a ejecutar al iniciar la aplicacion
  public void run(String... args) throws Exception{
    //guarda items en la base de datos
    itemServices.saveitem(new Item(null, "Item 1"));
    itemServices.saveitem(new Item(null, "Item 2"));

    //guarda personas en la base de datos
    Persona persona1 = new Persona(null, "Oscar", "Hendrix" ,19);
    personaServices.savePerson(persona1);
    Persona persona2 = new Persona(null, "Cruz", "Aranda" ,21);
    personaServices.savePerson(persona2);

    //guarda cuentas en la base de datos
    cuentaServices.saveCuenta(new Cuenta(null, "Oscar@gmail.com", "123456", persona1, true));
    cuentaServices.saveCuenta(new Cuenta(null, "Cruz@gmail.com", "123456-10", persona2, true));

  }

  @Bean //indica que es un bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) //indica que es un docket de swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.howdoinjava.demo.controllers")) // Reemplaza por el paquete de tus controladores
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()); //indica la informacion de la api de swagger 
                //en este caso se llama al metodo apiInfo
    }

    private ApiInfo apiInfo() { 
        return new ApiInfoBuilder()
                .title("APIs de Entrenamiento")
                .description("Documentación de la API de entrenamiento de Spring Boot")
                .version("1.0.0")
                .build(); //indica la informacion de la api de swagger 
                //en este caso se llama al metodo apiInfo
                
    }

}
