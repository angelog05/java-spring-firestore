package com.example.demo.models.dto;

import lombok.Data;

@Data
public class ApplicantDTO {
  String name;
  String lastName;
  String rut;


  public ApplicantDTO(String name, String lastName, String rut) {
    this.name = name;
    this.lastName = lastName;
    this.rut = rut;
  }
  
}
