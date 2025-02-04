package com.cgigueira.universalpetcare.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.exception.UserAlreadyExistsException;
import com.cgigueira.universalpetcare.repository.UserRepository;

@Component
public class SimpleUserFactory implements UserFactory {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private VeterinarianFactory veterinarianFactory;

  @Autowired
  private PatientFactory patientFactory;
  
  @Autowired
  private AdminFactory adminFactory;

  @Override
  public Response createUser(UserDto registrationRequest) {
    if (this.userRepository.existsByEmail(registrationRequest.getEmail()))
      throw new UserAlreadyExistsException("This email already exists! Try another");
    
    switch (registrationRequest.getUserType()) {
      case "VET": 
        return this.veterinarianFactory.createVeterinarian(registrationRequest);

      case "PATIENT": 
        return this.patientFactory.createPatient(registrationRequest);

      case "ADMIN": 
        return this.adminFactory.createAdmin(registrationRequest);

      default:
        return null;
    }
  }

}
