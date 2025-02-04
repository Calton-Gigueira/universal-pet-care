package com.cgigueira.universalpetcare.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.dto.VeterinarianDto;
import com.cgigueira.universalpetcare.mapper.EntityDtoMapper;
import com.cgigueira.universalpetcare.model.Veterinarian;
import com.cgigueira.universalpetcare.repository.VeterinarianRepository;

@Service
public class VeterinarianFactory {

  @Autowired
  private VeterinarianRepository veterinarianRepository;

  @Autowired
  private EntityDtoMapper entityDtoMapper;

  public Response createVeterinarian(UserDto registrationRequest) {
    Veterinarian veterinarian = new Veterinarian();
    this.entityDtoMapper.setCommonAttributes(registrationRequest, veterinarian);
    veterinarian.setSpecialization(registrationRequest.getSpecialization());
    this.veterinarianRepository.save(veterinarian);

    VeterinarianDto veterinarianDto = this.entityDtoMapper.mapVeterinarianToDtoBasic(veterinarian);
    return Response.builder()
      .status(200)
      .message("Veterinarian reigisterd successfully!")
      .veterinarianDto(veterinarianDto)
      .build();
  }

}
