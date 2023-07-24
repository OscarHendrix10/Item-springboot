package com.howdoinjava.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Persona")
public class Persona {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  @NotBlank(message = "Nombre is mandatory")
  private String nombre;

  @Column(nullable = false, length = 50)
  @NotBlank(message = "Apellido is mandatory")
  private String apellido;
  
  @Column(nullable = false)
  private int edad;

}
