package com.cgigueira.universalpetcare.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {

  private Long id;
  private String firstName;
  private String lastName;
  private String gender;
  private String phoneNumber;
  private String email;
  private String password;
  private String userType;
  private boolean isEnabled;
  private final LocalDateTime CREATED_AT = LocalDateTime.now();

}
