package com.example.demo.validation;

public class Rut {
  public static boolean isValid(String rut) {
    rut = rut.toUpperCase().replaceAll("[.-]", "");

    char dv = rut.charAt(rut.length() - 1);
    rut = rut.substring(0, rut.length() - 1);

    try {
        int intRut = Integer.parseInt(rut);
        return isValidRut(intRut, dv);
    } catch (NumberFormatException e) {
        return false;
    }
  }

  private static boolean isValidRut(int rut, char dv) {
    int m = 0, s = 1;
    for (; rut != 0; rut /= 10) {
        s = (s + rut % 10 * (9 - m++ % 6)) % 11;
    }
    return dv == (char) (s != 0 ? s + 47 : 75);
  }
}
