package com.howdoinjava.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Cuenta")
public class Cuenta {
   
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(nullable = false, length = 50)
  @Email
  private String email;

  @Column(nullable = false, length = 50)
  private String password;

  @ManyToOne
  @JoinColumn(name = "persona_id")
  private Persona persona_id;

    
}
