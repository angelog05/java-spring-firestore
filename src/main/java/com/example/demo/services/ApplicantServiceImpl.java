package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.exeptions.NotFoundException;
import com.example.demo.models.Applicant;
import com.example.demo.repositories.ApplicantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class ApplicantServiceImpl implements ApplicantService {

  @Autowired
  private ApplicantRepository repository;

  /** 
   * Store an applicant in repository
   * 
   * @param applicant object to be stored
   * @return Applicant with generated id
   */
  @Override
  public Applicant create(Applicant applicant) {
    return repository.save(applicant);
  }

  
  /** 
   * Search for an applicant by id
   * 
   * @param id
   * @return Applicant
   */
  @Override
  public Applicant findById(Integer id) {
    Optional<Applicant> result = repository.findById(id);

    return result.orElse(null);
  }

  
  /** 
   * Search all registered applicants
   * 
   * @return List<Applicant> list of applicants
   */
  @Override
  public List<Applicant> findAll() {
    return repository.findAll();
  }

  
  /** 
   * Find and update an applicant by id
   * 
   * @param id
   * @param applicant object to be stored
   * @return Applicant with his new data
   */
  @Override
  public Applicant update(Integer id, Applicant applicant) {

    Applicant storedApplicant = this.findById(id);

    if(storedApplicant == null){
      throw new NotFoundException();
    }

    storedApplicant.setLastName(applicant.getLastName());
    storedApplicant.setName(applicant.getName());
    storedApplicant.setRut(applicant.getRut());

    return repository.save(storedApplicant);
  }

  
  /** 
   * Find and delete an applicant by id
   * 
   * @param id
   * @return boolean confirmation
   */
  @Override
  public boolean delete(Integer id) {
    Applicant storedApplicant = this.findById(id);

    if(storedApplicant != null) {
      repository.deleteById(id);
      return true;
    }
    
    return false;
  }

  
  /**
   * Search all registered applicants
   * 
   * @param pageable
   * @return Page<Applicant> all registered applicants with pagination
   */
  @Override
  public Page<Applicant> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }
  
}
