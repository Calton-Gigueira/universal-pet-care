package com.cgigueira.universalpetcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgigueira.universalpetcare.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

  Appointment findByAppointmentNumber(String appointmentNumber);

}
