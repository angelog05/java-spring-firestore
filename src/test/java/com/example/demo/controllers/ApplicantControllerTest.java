// package com.example.demo.controllers;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.util.ArrayList;
// import java.util.List;

// import com.example.demo.models.Applicant;
// import com.example.demo.services.ApplicantService;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// @AutoConfigureMockMvc
// @WebMvcTest //@WebMvcTest(controllers = ApplicantController.class)
// public class ApplicantControllerTest {
//   @Autowired
//   private MockMvc mockMvc;

//   @MockBean
//   ApplicantService service;

//   ApplicantController controller;

//   @Test
//   public void testFindAllPaginated() throws Exception {
//       List<Applicant> applicants = new ArrayList<>();
//       applicants.add(new Applicant(1, "Juan", "Pérez", "1-9"));

//       Pageable pageable = PageRequest.of(0, 10);
//       Page<Applicant> page = new PageImpl<Applicant>(applicants, pageable, applicants.size());
      
//       System.out.println("Page: " + page.getNumber());

//       when(service.findAll(pageable)).thenReturn(page);

//       mockMvc.perform(
//               get("/applicants"))
//               .andExpect(status().isOk());
//   }

//   @Test
//   public void testFindById() throws Exception {
//     Applicant applicant = new Applicant(1, "Juan", "Perez", "1-9");

//     when(service.findById(1)).thenReturn(applicant);

//     mockMvc.perform(
//       get("/applicants/1"))
//       .andExpect(status().isOk())
//       .andExpect(jsonPath("$.id").value(1))
//       .andExpect(jsonPath("$.name").value("Juan"))
//       .andExpect(jsonPath("$.lastName").value("Perez"))
//       .andExpect(jsonPath("$.rut").value("1-9"));
//   }

//   @Test
//   public void testFindAll() throws Exception {
//     List<Applicant> applicants = new ArrayList<>();
//     applicants.add(new Applicant(1, "Juan", "Perez", "1-9"));

//     when(service.findAll()).thenReturn(applicants);

//     mockMvc.perform(
//       get("/applicants/all"))
//         .andExpect(status().isOk())
//         .andExpect(jsonPath("$.[0].id").value(1))
//         .andExpect(jsonPath("$.[0].name").value("Juan"))
//         .andExpect(jsonPath("$.[0].lastName").value("Perez"))
//         .andExpect(jsonPath("$.[0].rut").value("1-9"));
//   }

//   @Test
//   public void testDeletebyId() throws Exception {
//     Boolean message = true;

//     when(service.delete(1)).thenReturn(message);

//     mockMvc.perform(
//       delete("/applicants/1"))
//         .andExpect(status().isOk());
//   }

//   @Test
//   public void testUpdate() throws Exception {
//     Applicant applicant = new Applicant(1, "Juan", "Perez", "1-9");

//     when(service.update(1, applicant)).thenReturn(applicant);

//     mockMvc.perform(
//       put("/applicants/1").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Juan\",\"lastName\":\"Pérez\",\"rut\":\"1-9\"}"))
//         .andExpect(status().isUnauthorized());
//   }

//   @Test
//   public void testCreateApplicant() throws Exception {
//     Applicant applicant = new Applicant(1, "Juan", "Perez", "1-9");
  
//     when(service.create(any())).thenReturn(applicant);

//     mockMvc.perform(
//       post("/applicants").contentType(MediaType.APPLICATION_JSON).content("{\"id\":1,\"name\":\"Juan\",\"lastName\":\"Pérez\",\"rut\":\"1-9\"}"))
//         .andExpect(status().isCreated())
//         .andExpect(jsonPath("$.id").value(1))
//         .andExpect(jsonPath("$.name").value("Juan"))
//         .andExpect(jsonPath("$.lastName").value("Perez"))
//         .andExpect(jsonPath("$.rut").value("1-9"));
//   }
	
// }
