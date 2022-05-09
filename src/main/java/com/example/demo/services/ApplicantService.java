package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Applicant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Applicant Crud
 */
public interface ApplicantService {
  
  /** 
   * Create an applicant
   * 
   * @param applicant object to be created
   */
  Applicant create(Applicant applicant);
  
  /** 
   * Search applicant by id
   * 
   * @param id applicant identifier
   */
  Applicant findById(Integer id);

  /** 
   * Search all applicants with pagination
   * 
   * @param pageable of type Pageable for pagination
   */
  Page<Applicant> findAll(Pageable pageable);

  /** 
   * Search all applicants
   * 
   */
  List<Applicant> findAll();

  /** 
   * Update applicant by id
   * 
   * @param id applicant identifier
   * @param applicant object to be updated
   */
  Applicant update(Integer id, Applicant applicant);

  /** 
   * Delete applicant by id
   * 
   * @param id applicant identifier
   * @param applicant object to be updated
   */
  boolean delete(Integer id);

}
