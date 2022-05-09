package com.example.demo.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RutTest {
  
  @Test
  public void testValidRut() {

    String rut = "17080257-8";
    boolean result = Rut.isValid(rut);

    assertTrue(result);
  }

  @Test
  public void testInvalidRut() {

    String rut = "12345678-9";
    boolean result = Rut.isValid(rut);

    assertFalse(result);
  }

  @Test
  public void testValidRutLowerCaseK() {

    String rut = "555.554-k";
    boolean result = Rut.isValid(rut);

    assertTrue(result);
  }

  @Test
  public void testValidRutUpperCaseK() {

    String rut = "555.554-K";
    boolean result = Rut.isValid(rut);

    assertTrue(result);
  }

  @Test
  public void testInvalidShortRut() {

    String rut = "1-1";
    boolean result = Rut.isValid(rut);

    assertFalse(result);
  }

  @Test
  public void testValidRutWithout() {

    String rut = "1111111-1";
    boolean result = Rut.isValid(rut);

    assertFalse(result);
  }

  @Test
  public void testValidRutunscripted() {

    String rut = "11111111";
    boolean result = Rut.isValid(rut);

    assertFalse(result);
  }

  @Test
  public void testInvalidRutWithInitialLetter() {

    String rut = "a-9";
    boolean result = Rut.isValid(rut);

    assertFalse(result); 
  }
}
