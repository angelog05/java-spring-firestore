package com.example.demo.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import com.example.demo.exeptions.NotFoundException;
import com.example.demo.models.Appointment;
import com.example.demo.models.dto.AppointmentDTO;
import com.example.demo.services.AppointmentServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

  @PutMapping("/{id}")
  public AppointmentDTO update(@Valid @PathVariable String id, @RequestBody Appointment appointment) {
    AppointmentDTO currAppointment = new AppointmentDTO();
    currAppointment.setEmail(appointment.getEmail());
    currAppointment.setHour(appointment.getHour());
    currAppointment.setDate(appointment.getDate());

    return service.update(id, currAppointment);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody AppointmentDTO create(@Valid @RequestBody Appointment appointment) throws IOException {
    AppointmentDTO currAppointment = new AppointmentDTO();
    currAppointment.setEmail(appointment.getEmail());
    currAppointment.setHour(appointment.getHour());
    currAppointment.setDate(appointment.getDate());
    
    return service.create(currAppointment);
  }

}
