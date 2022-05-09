package com.example.demo.repositories;

import com.example.demo.models.Applicant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer>{
  // funciones que no existan, que no esten en la interfaz -> Aquí
}
