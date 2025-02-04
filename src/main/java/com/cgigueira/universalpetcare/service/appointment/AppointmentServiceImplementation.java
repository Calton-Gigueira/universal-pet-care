package com.cgigueira.universalpetcare.service.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgigueira.universalpetcare.dto.AppointmentDto;
import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.enums.AppointmentStatus;
import com.cgigueira.universalpetcare.exception.ResourceNotFoundException;
import com.cgigueira.universalpetcare.mapper.EntityDtoMapper;
import com.cgigueira.universalpetcare.model.Appointment;
import com.cgigueira.universalpetcare.model.User;
import com.cgigueira.universalpetcare.repository.AppointmentRepository;
import com.cgigueira.universalpetcare.service.user.UserService;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private EntityDtoMapper entityDtoMapper;

  @Autowired
  private UserService userService;

  @Override
  public Response createAppointment(AppointmentDto request, Long senderId, Long recipientId) {
    UserDto senderDto = this.userService.getUserById(senderId).getUserDto();
    UserDto recipientDto = this.userService.getUserById(recipientId).getUserDto();

    User sender = this.entityDtoMapper.mapDtoToUserBasic(senderDto);
    User recipient = this.entityDtoMapper.mapDtoToUserBasic(recipientDto);

    Appointment appointment = new Appointment();
    
    appointment.setPatient(sender);
    appointment.setVeterinarian(recipient);
    appointment.setAppointmentNumber();
    appointment.setReason(request.getReason());
    appointment.setDate(request.getDate());
    appointment.setTime(request.getTime());
    appointment.setStatus(AppointmentStatus.WAITING_FOR_APPROVAL);
    AppointmentDto appointmentDto = this.entityDtoMapper.mapAppointmentToDtoBasic(appointment);
    
    return Response.builder()
      .status(201)
      .message("Appointment created successfully")
      .appointmentDto(appointmentDto)
      .build();
  }

  @Override
  public Response getAllApponitments() {
    List<Appointment> appointments = this.appointmentRepository.findAll();
    List<AppointmentDto> appointmentDtos = appointments.stream()
      .map(this.entityDtoMapper::mapAppointmentToDtoBasic)
        .toList();
    return Response.builder()
      .status(302)
      .message("Appointments")
      .appointmentDtos(appointmentDtos)
      .build();
  }

  @Override
  public Response updateAppointment(Long appointmentId, AppointmentDto request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateAppointment'");
  }

  @Override
  public Response deleteAppointment(Long appointmentId) {
    this.getAppointmentById(appointmentId);
    this.appointmentRepository.deleteById(appointmentId);
    return Response.builder()
      .status(200)
      .message("Appointment deleted successfully")
      .build();
  }

  @Override
  public Response getAppointmentById(Long appointmentId) {
    Appointment appointment = this.appointmentRepository.findById(appointmentId)
        .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
    AppointmentDto appointmentDto = this.entityDtoMapper.mapAppointmentToDtoBasic(appointment);
    return Response.builder()
      .status(302)
      .message("Appointment found")
      .appointmentDto(appointmentDto)
      .build();
  }

  @Override
  public Response getAppointmentByNumber(String appointmentNumber) {
    Appointment appointment = this.appointmentRepository.findByAppointmentNumber(appointmentNumber);
    AppointmentDto appointmentDto = this.entityDtoMapper.mapAppointmentToDtoBasic(appointment);
    return Response.builder()
      .status(302)
      .message("Appointment found")
      .appointmentDto(appointmentDto)
      .build();
  }

}
