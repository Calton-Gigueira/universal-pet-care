package com.cgigueira.universalpetcare.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cgigueira.universalpetcare.dto.PatientDto;
import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.enums.UserRole;
import com.cgigueira.universalpetcare.mapper.EntityDtoMapper;
import com.cgigueira.universalpetcare.model.Patient;
import com.cgigueira.universalpetcare.repository.PatientRepository;

@Service
public class PatientFactory {

  @Autowired
  private EntityDtoMapper entityDtoMapper;

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Response createPatient(UserDto registrationRequest) {
    Patient patient = new Patient();
    this.entityDtoMapper.setCommonAttributes(registrationRequest, patient);
    String encodedPassword = this.passwordEncoder.encode(registrationRequest.getPassword());
    patient.setPassword(encodedPassword);
    patient.setRole(UserRole.USER);
    Patient savedPatient = this.patientRepository.save(patient);

    PatientDto patientDto = this.entityDtoMapper.mapPatientToDtoBasic(savedPatient);
    return Response.builder()
      .status(201)
      .message("Patient registered successfully")
      .patientDto(patientDto)
      .build();
  }

}
