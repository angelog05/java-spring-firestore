package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.exeptions.NotFoundException;
import com.example.demo.models.Applicant;
import com.example.demo.models.dto.ApplicantDTO;
import com.example.demo.services.ApplicantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {
  
  private ApplicantService service;
  

  @Autowired
  ApplicantController(ApplicantService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public Applicant findById(@PathVariable Integer id) {
    
    Applicant result = service.findById(id);

    if(result == null) {
      throw new NotFoundException();
    }

    return result;
  }

  @GetMapping
  public Page<Applicant> findPaginated(Pageable pageable) {
    // Pageable newPageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
    
    return service.findAll(pageable);
  }

  @GetMapping("/all")
  public List<Applicant> findAll() {
    return service.findAll();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@Valid @PathVariable Integer id) {
    boolean response = service.delete(id);
    
    if(response) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    throw new NotFoundException();
  }

  @PutMapping("/{id}")
  public Applicant update(@Valid @PathVariable Integer id, @RequestBody ApplicantDTO applicant) {
    Applicant currentApplicant = new Applicant();
    currentApplicant.setName(applicant.getName());
    currentApplicant.setLastName(applicant.getLastName());
    currentApplicant.setRut(applicant.getRut());

    return service.update(id, currentApplicant);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody Applicant create(@Valid @RequestBody ApplicantDTO applicant) {
    Applicant currentApplicant = new Applicant();
    currentApplicant.setName(applicant.getName());
    currentApplicant.setLastName(applicant.getLastName());
    currentApplicant.setRut(applicant.getRut());
    
    return service.create(currentApplicant);
  }

}
