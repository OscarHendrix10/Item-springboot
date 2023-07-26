package com.howdoinjava.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //es una entidad de la base de datos
@Data //genera los getters y setters
@AllArgsConstructor //genera un constructor con todos los argumentos
@NoArgsConstructor// genera un constructor vacio
@Table(name = "Cuenta")// crea la tabla en la base de datos
public class Cuenta {
   //atributos de la clase
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;

  
  @Column(nullable = false, length = 50) //no puede ser nulo y tiene un maximo de 50 caracteres
  @Email //valida que sea un correo
  private String email;

  @Column(nullable = false, length = 50) // no puede ser nulo y tiene un maximo de 50 caracteres
  private String password;

  @ManyToOne //muchas cuentas pueden pertenecer a una persona
  @JoinColumn(name = "persona_id") //nombre de la columna que se va a crear en la tabla
  private Persona persona_id;

    
}
