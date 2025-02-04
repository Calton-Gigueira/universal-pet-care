package com.cgigueira.universalpetcare.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import com.cgigueira.universalpetcare.enums.AppointmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String reason;
  private LocalDate date;
  private LocalTime time;
  private String appointmentNumber;

  @Enumerated(EnumType.STRING)
  private AppointmentStatus status;

  @Column(name = "created_at", updatable = false)
  private final LocalDateTime CREATED_AT = LocalDateTime.now();
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sender")
  private User patient;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "recipient")
  private User veterinarian;

  public void addPatient(User sender) {
    this.setPatient(sender);
    if (sender.getAppointments() == null) {
      sender.setAppointments(new ArrayList<Appointment>());
    }
    sender.getAppointments().add(this);
  }

  public void addVeterinarian(User recipient) {
    this.setVeterinarian(recipient);
    if (recipient.getAppointments() == null) {
      recipient.setAppointments(new ArrayList<Appointment>());
    }
    recipient.getAppointments().add(this);
  }

  public void setAppointmentNumber() {
    this.appointmentNumber = String.valueOf(new Random().nextLong()).substring(1, 11);
  }

}
