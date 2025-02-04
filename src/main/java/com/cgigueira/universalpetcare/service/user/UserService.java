package com.cgigueira.universalpetcare.service.user;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;

public interface UserService {

  public Response registerUser(UserDto registrationRequest);

}
