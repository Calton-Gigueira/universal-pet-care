package com.cgigueira.universalpetcare.mapper;

import org.springframework.stereotype.Component;

import com.cgigueira.universalpetcare.dto.AdminDto;
import com.cgigueira.universalpetcare.dto.PatientDto;
import com.cgigueira.universalpetcare.dto.VeterinarianDto;
import com.cgigueira.universalpetcare.model.Admin;
import com.cgigueira.universalpetcare.model.Patient;
import com.cgigueira.universalpetcare.model.Veterinarian;

@Component
public class EntityDtoMapper {

  public AdminDto mapAdminToDtoBasic(Admin admin) {
    AdminDto adminDto = new AdminDto();
    adminDto.setId(admin.getId());
    adminDto.setFirstName(admin.getFirstName());
    adminDto.setLastName(admin.getLastName());
    adminDto.setGender(admin.getGender());
    adminDto.setEmail(admin.getEmail());
    adminDto.setPassword(admin.getPassword());
    adminDto.setUserType(admin.getUserType());
    adminDto.setPhoneNumber(admin.getPhoneNumber());

    return adminDto;
  }

  public PatientDto mapPatientToDtoBasic(Patient patient) {
    PatientDto patientDto = new PatientDto();
    patientDto.setId(patient.getId());
    patientDto.setFirstName(patient.getFirstName());
    patientDto.setLastName(patient.getLastName());
    patientDto.setGender(patient.getGender());
    patientDto.setEmail(patient.getEmail());
    patientDto.setPassword(patient.getPassword());
    patientDto.setUserType(patient.getUserType());
    patientDto.setPhoneNumber(patient.getPhoneNumber());

    return patientDto;
  }

  public VeterinarianDto mapVeterinarianToDtoBasic(Veterinarian veterianrian) {
    VeterinarianDto veterianrianDto = new VeterinarianDto();
    veterianrianDto.setId(veterianrian.getId());
    veterianrianDto.setFirstName(veterianrian.getFirstName());
    veterianrianDto.setLastName(veterianrian.getLastName());
    veterianrianDto.setGender(veterianrian.getGender());
    veterianrianDto.setEmail(veterianrian.getEmail());
    veterianrianDto.setPassword(veterianrian.getPassword());
    veterianrianDto.setUserType(veterianrian.getUserType());
    veterianrianDto.setPhoneNumber(veterianrian.getPhoneNumber());

    return veterianrianDto;
  }

}
