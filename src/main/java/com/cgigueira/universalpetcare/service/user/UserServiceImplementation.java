package com.cgigueira.universalpetcare.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.factory.UserFactory;

@Service
public class UserServiceImplementation implements UserService {

  @Autowired
  private UserFactory userFactory;

  @Override
  public Response registerUser(UserDto registrationRequest) {
    return this.userFactory.createUser(registrationRequest);
  }

}
