package com.cgigueira.universalpetcare.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AppointmentDto {

  private Long id;
  private String reason;
  private LocalDate date;
  private LocalTime time;
  private String appointmentNumber;
  private String status;
  private LocalDateTime CREATED_AT;
  private UserDto patient;
  private UserDto veterinarian;

}
