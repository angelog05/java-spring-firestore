package com.example.demo.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.demo.exeptions.NotFoundException;
import com.example.demo.models.dto.AppointmentDTO;
import com.example.demo.services.AppointmentServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
  
  private AppointmentServiceImp service;
  

  @Autowired
  AppointmentController(AppointmentServiceImp service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public AppointmentDTO findById(@PathVariable String id) throws IOException, InterruptedException, ExecutionException {
    
    AppointmentDTO result = service.findById(id);

    if(result == null) {
      throw new NotFoundException();
    }

    return result;
  }

  // @GetMapping
  // public Page<Applicant> findPaginated(Pageable pageable) {
  //   // Pageable newPageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
    
  //   return service.findAll(pageable);
  // }

  @GetMapping("/all")
  public List<AppointmentDTO> findAll() throws IOException, InterruptedException, ExecutionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    return service.findAll();
  }

  // @DeleteMapping("/{id}")
  // public ResponseEntity<String> delete(@Valid @PathVariable Integer id) {
  //   boolean response = service.delete(id);
    
  //   if(response) {
  //     return new ResponseEntity<>(HttpStatus.OK);
  //   }
  //   throw new NotFoundException();
  // }

  // @PutMapping("/{id}")
  // public Applicant update(@Valid @PathVariable Integer id, @RequestBody ApplicantDTO applicant) {
  //   Applicant currentApplicant = new Applicant();
  //   currentApplicant.setName(applicant.getName());
  //   currentApplicant.setLastName(applicant.getLastName());
  //   currentApplicant.setRut(applicant.getRut());

  //   return service.update(id, currentApplicant);
  // }

  // @PostMapping()
  // @ResponseStatus(HttpStatus.CREATED)
  // public @ResponseBody Applicant create(@Valid @RequestBody ApplicantDTO applicant) {
  //   Applicant currentApplicant = new Applicant();
  //   currentApplicant.setName(applicant.getName());
  //   currentApplicant.setLastName(applicant.getLastName());
  //   currentApplicant.setRut(applicant.getRut());
    
  //   return service.create(currentApplicant);
  // }

}
