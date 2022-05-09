package com.example.demo.controllers;

import com.example.demo.models.Applicant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GreetingController {

  @GetMapping("/string")
  public String greeting() {
    Applicant app = new Applicant(1, "juan", "rambo", "1-8");

    return app.toString();
  }

  @GetMapping("/object")
  public Applicant findApplicant() {
    return new Applicant(2, "juan", "rambo", "1-8");
  }

  @GetMapping("/variables/{id}")
  public String showVariables(
    @PathVariable String id,
    // TODO: cuando son más parametros?
    @RequestParam(value = "page", defaultValue = "10") int page) { 

    return "id: " + id + "<br /> page: " + page;
  }
}
