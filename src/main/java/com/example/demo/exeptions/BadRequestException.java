package com.example.demo.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong parameter")
public class BadRequestException extends RuntimeException {
  
  // public BadRequestException(String value) {
  //   super(value);
  // }

  // public BadRequestException(String value, Throwable throwable) {
  //   super(value, throwable);
  // }
}
