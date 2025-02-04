package com.cgigueira.universalpetcare.mapper;

import org.springframework.stereotype.Component;

import com.cgigueira.universalpetcare.dto.AdminDto;
import com.cgigueira.universalpetcare.dto.PatientDto;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.dto.VeterinarianDto;
import com.cgigueira.universalpetcare.model.Admin;
import com.cgigueira.universalpetcare.model.Patient;
import com.cgigueira.universalpetcare.model.User;
import com.cgigueira.universalpetcare.model.Veterinarian;

@Component
public class EntityDtoMapper {

  public void setCommonAttributes(UserDto source, User target) {
    target.setId(source.getId());
    target.setFirstName(source.getFirstName());
    target.setLastName(source.getLastName());
    target.setGender(source.getGender());
    target.setEmail(source.getEmail());
    target.setPassword(source.getPassword());
    target.setUserType(source.getUserType());
    target.setPhoneNumber(source.getPhoneNumber());
    target.setEnabled(source.isEnabled());
  }

  public UserDto mapUserToDtoBasic(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setFirstName(user.getFirstName());
    userDto.setLastName(user.getLastName());
    userDto.setGender(user.getGender());
    userDto.setEmail(user.getEmail());
    userDto.setPassword(user.getPassword());
    userDto.setUserType(user.getUserType());
    userDto.setPhoneNumber(user.getPhoneNumber());
    userDto.setEnabled(user.isEnabled());
    userDto.setSpecialization(user.getSpecialization());
    
    return userDto;
  }

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
    adminDto.setEnabled(admin.isEnabled());
    
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
    patientDto.setEnabled(patient.isEnabled());
    
    return patientDto;
  }

  public VeterinarianDto mapVeterinarianToDtoBasic(Veterinarian veterinarian) {
    VeterinarianDto veterinarianDto = new VeterinarianDto();
    veterinarianDto.setId(veterinarian.getId());
    veterinarianDto.setFirstName(veterinarian.getFirstName());
    veterinarianDto.setLastName(veterinarian.getLastName());
    veterinarianDto.setGender(veterinarian.getGender());
    veterinarianDto.setEmail(veterinarian.getEmail());
    veterinarianDto.setPassword(veterinarian.getPassword());
    veterinarianDto.setUserType(veterinarian.getUserType());
    veterinarianDto.setPhoneNumber(veterinarian.getPhoneNumber());
    veterinarianDto.setSpecialization(veterinarian.getSpecialization());
    veterinarianDto.setEnabled(veterinarian.isEnabled());

    return veterinarianDto;
  }

}
