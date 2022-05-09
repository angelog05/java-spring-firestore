package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.demo.models.Applicant;
import com.example.demo.repositories.ApplicantRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {ApplicantService.class, ApplicantServiceImpl.class})
public class ApplicantServiceTest {
  
  @MockBean
  ApplicantRepository repository;

  @Autowired
  ApplicantService service;

  @Test
  void testFindById() {
    Optional<Applicant> applicant = Optional.of(
      new Applicant(1, "Rick", "Sanchez", "17080257-8"));
    when(repository.findById(anyInt())).thenReturn(applicant);

    var result = service.findById(1);

    assertEquals(1, result.getId());
    assertEquals("Rick", result.getName());
    assertEquals("Sanchez", result.getLastName());
  }
}
