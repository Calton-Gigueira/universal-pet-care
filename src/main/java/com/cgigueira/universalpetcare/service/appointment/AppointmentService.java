package com.cgigueira.universalpetcare.service.appointment;

import com.cgigueira.universalpetcare.dto.AppointmentDto;
import com.cgigueira.universalpetcare.dto.Response;

public interface AppointmentService {

  public Response createAppointment(AppointmentDto request, Long senderId, Long recipientId);

  public Response getAllApponitments();

  public Response updateAppointment(Long appointmentId, AppointmentDto request);

  public Response deleteAppointment(Long appointmentId);

  public Response getAppointmentById(Long appointmentId);

  public Response getAppointmentByNumber(String appointmentNumber);

}
