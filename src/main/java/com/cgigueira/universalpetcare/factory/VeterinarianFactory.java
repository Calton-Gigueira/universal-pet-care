package com.cgigueira.universalpetcare.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.dto.VeterinarianDto;
import com.cgigueira.universalpetcare.enums.UserRole;
import com.cgigueira.universalpetcare.mapper.EntityDtoMapper;
import com.cgigueira.universalpetcare.model.Veterinarian;
import com.cgigueira.universalpetcare.repository.VeterinarianRepository;

@Service
public class VeterinarianFactory {

  @Autowired
  private VeterinarianRepository veterinarianRepository;

  @Autowired
  private EntityDtoMapper entityDtoMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Response createVeterinarian(UserDto registrationRequest) {
    Veterinarian veterinarian = new Veterinarian();
    this.entityDtoMapper.setCommonAttributes(registrationRequest, veterinarian);
    veterinarian.setSpecialization(registrationRequest.getSpecialization());
    veterinarian.setRole(UserRole.USER);
    String encodedPassword = this.passwordEncoder.encode(registrationRequest.getPassword());
    veterinarian.setPassword(encodedPassword);
    Veterinarian savedVeterinarian = this.veterinarianRepository.save(veterinarian);

    VeterinarianDto veterinarianDto = this.entityDtoMapper.mapVeterinarianToDtoBasic(savedVeterinarian);
    return Response.builder()
      .status(201)
      .message("Veterinarian reigisterd successfully!")
      .veterinarianDto(veterinarianDto)
      .build();
  }

}
