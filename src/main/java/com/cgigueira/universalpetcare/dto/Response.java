package com.cgigueira.universalpetcare.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

  private int status;
  private String message;
  private String token;

  private AdminDto adminDto;
  private List<AdminDto> adminDtos;

  private PatientDto patientDto;
  private List<PatientDto> patientDtos;

  private VeterinarianDto veterinarianDto;
  private List<VeterinarianDto> veterinarianDtos;

  private UserDto userDto;
  private List<UserDto> userDtos;

  private AppointmentDto appointmentDto;
  private List<AppointmentDto> appointmentDtos;

}
