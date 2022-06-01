package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
  
  @Id
  @NotBlank
  private String email;
  
  @NotBlank
  private String hour;

  @NotBlank
  private String date;
  
}
