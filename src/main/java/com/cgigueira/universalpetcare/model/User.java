package com.cgigueira.universalpetcare.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String gender;

  @Column(name = "phone_number")
  private String phoneNumber;
  
  @Column(unique = true)
  private String email;
  private String password;
  private String userType;
  private boolean isEnabled;

  @Transient
  private String specialization;
  
  @Column(name = "created_at")
  private final LocalDateTime CREATED_AT = LocalDateTime.now();
  
  @Transient
  private List<Appointment> appointments;

}
