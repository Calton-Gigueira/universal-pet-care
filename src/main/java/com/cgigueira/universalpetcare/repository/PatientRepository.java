package com.cgigueira.universalpetcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgigueira.universalpetcare.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
