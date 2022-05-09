package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RutValidator implements ConstraintValidator<ValidRut, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    
    return Rut.isValid(value);
  }


  
}
