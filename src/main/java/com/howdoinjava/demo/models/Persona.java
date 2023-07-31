package com.howdoinjava.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //es una entidad de la base de datos
@Data //genera los getters y setters
@AllArgsConstructor //genera un constructor con todos los argumentos
@NoArgsConstructor// genera un constructor vacio
@Table(name = "person") // crea la tabla en la base de datos
public class Persona {
    
  //atributos de la clase
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrementable
  private Long id;

  @Column(nullable = false, length = 50) // no puede ser nulo y tiene un maximo de 50 caracteres
  @NotBlank(message = "Nombre is mandatory") //valida que no este vacio
  private String nombre;

  @Column(nullable = false, length = 50)// no puede ser nulo y tiene un maximo de 50 caracteres
  @NotBlank(message = "Apellido is mandatory")//valida que no este vacio
  private String apellido;
  
  @Column(nullable = false)// no puede ser nulo
  private int edad;

}
