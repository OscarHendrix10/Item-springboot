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
@Table(name = "Item")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(nullable = false, length = 50)
  @NotBlank
  private String name;
  
}
